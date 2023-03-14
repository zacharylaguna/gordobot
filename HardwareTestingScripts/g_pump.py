import time
import sys
from hx711 import HX711
import RPi.GPIO as GPIO

# for liquid ingredients
class Pumps:
    # setup GPIO for breakout board and pumps
    GPIO.setmode(GPIO.BCM)
    GPIO.setup(19, GPIO.OUT) # Connected to AIN1
    GPIO.setup(26, GPIO.OUT) # Connected to AIN2
    GPIO.setup(20, GPIO.OUT) # Connected to BIN1
    GPIO.setup(16, GPIO.OUT) # Connected to BIN2

    def dispensePump(self, ingredientPos, weight):
        if(ingredientPos > 5 or ingredientPos < 4):
            print("ERROR: Invalid liquid ingredient positon...")
            return
        print("dispensing ingredient...")

        # handling scale initializaton
        hx = HX711(5, 6)                                    
        hx.set_reading_format("MSB", "MSB")
        hx.set_reference_unit(477)
        hx.reset()
        hx.tare()
        
        # getting current weight and calculating final weight
        currWeight = hx.get_weight(5)
        finalWeight = currWeight + weight
        timeout = 0
        try:
            # measure and dispense until weight satisified or timeout
            while(finalWeight >= currWeight + 20 and timeout <= 200):
                # measure current weight
                currWeight = round(hx.get_weight(5))
                hx.power_down()
                hx.power_up()
                print("Weight (in grams):", currWeight)

                # dispense
                if(ingredientPos == 4):
                    GPIO.output(19, GPIO.HIGH) # Set AIN1
                    GPIO.output(26, GPIO.LOW) # Set AIN2
                elif(ingredientPos == 5):
                    GPIO.output(20, GPIO.HIGH) # Set BIN1
                    GPIO.output(16, GPIO.LOW) # Set BIN2
                time.sleep(.1)

                timeout += 1
            # Reset pins
            GPIO.output(19, GPIO.LOW) # Set AIN1
            GPIO.output(26, GPIO.LOW) # Set AIN2
            GPIO.output(20, GPIO.LOW) # Set BIN1
            GPIO.output(16, GPIO.LOW) # Set BIN2
        except:
            # Reset pins
            GPIO.output(19, GPIO.LOW) # Set AIN1
            GPIO.output(26, GPIO.LOW) # Set AIN2
            GPIO.output(20, GPIO.LOW) # Set BIN1
            GPIO.output(16, GPIO.LOW) # Set BIN2

