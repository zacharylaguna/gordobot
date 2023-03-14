import RPi.GPIO as GPIO
import pigpio
from hx711 import HX711
import time
import sys

# for control of top ingredients
class TopServo:
    # Settings specific to DS3218 servo from data sheet
    servo = 2
    pwm = pigpio.pi()
    pwm.set_mode(servo, pigpio.OUTPUT)
    pwm.set_PWM_frequency(servo, 50)
    currPWM = 500
    offsetPWM = 500

    if not pwm.connected:
            print("Failed to connect")
            exit()

    def changeIngredient(self, ingredientPos):
        if(ingredientPos > 3 or ingredientPos < 0):
            print("ERROR: Invalid solid ingredient positon...")
            return

        print("changing ingredient...")

        # experimentally determined PWMs
        newPWM = 500
        if(ingredientPos == 0):
            newPWM = 500
        elif(ingredientPos == 1):
            newPWM = 1140
        elif(ingredientPos == 2):
            newPWM = 1780
        elif(ingredientPos == 3):
            newPWM = 2440
        
        # saving offset PWM and current PWM
        TopServo.currPWM = newPWM
        TopServo.offsetPWM = newPWM + 200
        if(TopServo.offsetPWM > 2500):
            TopServo.offsetPWM = newPWM - 200        

        TopServo.pwm.set_servo_pulsewidth(TopServo.servo, TopServo.offsetPWM)
        time.sleep(2.0) # can't tell when servo is done moving, should be enough time

    def dispenseIngredient(self, weight):
        if(weight > 5000 or weight < 0):
            print("ERROR: Weight must be between 0 and 5000 grams...")
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

        # measure and dispense until weight satisified or timeout
        try:
            while(finalWeight >= currWeight and timeout <= 100):
                # measure current weight
                currWeight = round(hx.get_weight(5))
                hx.power_down()
                hx.power_up()
                print("Weight (in grams):", currWeight)

                # dispense by shaking
                TopServo.pwm.set_servo_pulsewidth(TopServo.servo, TopServo.currPWM)
                time.sleep(.2)
                TopServo.pwm.set_servo_pulsewidth(TopServo.servo, TopServo.offsetPWM)
                time.sleep(.2)

                timeout += 1
        except:
            TopServo.pwm.set_servo_pulsewidth(TopServo.servo, TopServo.offsetPWM)
            time.sleep(1.0) # can't tell when servo is done moving, should be enough time

