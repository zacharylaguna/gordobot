# Import required modules
import time
import RPi.GPIO as GPIO

# Declare the GPIO settings
GPIO.setmode(GPIO.BCM)

# set up GPIO pins
print("Setting up pins")
GPIO.setup(19, GPIO.OUT) # Connected to AIN1
GPIO.setup(26, GPIO.OUT) # Connected to AIN2

GPIO.setup(20, GPIO.OUT) # Connected to BIN1
GPIO.setup(16, GPIO.OUT) # Connected to BIN2

# Drive the motor clockwise
print("Driving motors")
# Motor A:
GPIO.output(19, GPIO.HIGH) # Set AIN1
GPIO.output(26, GPIO.LOW) # Set AIN2
# Motor B:
GPIO.output(20, GPIO.HIGH) # Set BIN1
GPIO.output(16, GPIO.LOW) # Set BIN2

# # Set the motor speed
# print("Setting speed of motors")
# # Motor A:
# GPIO.output(7, GPIO.HIGH) # Set PWMA
# # Motor B:
# GPIO.output(18, GPIO.HIGH) # Set PWMB

# # Disable STBY (standby)
# GPIO.output(13, GPIO.HIGH)

# Wait 5 seconds
try:
    while(True):
        time.sleep(5)
except:
    print("Resetting all pins")
    GPIO.output(19, GPIO.LOW) # Set AIN1
    GPIO.output(26, GPIO.LOW) # Set AIN2
    # GPIO.output(7, GPIO.LOW) # Set PWMA
    # GPIO.output(13, GPIO.LOW) # Set STBY
    GPIO.output(20, GPIO.LOW) # Set BIN1
    GPIO.output(16, GPIO.LOW) # Set BIN2
    # GPIO.output(18, GPIO.LOW) # Set PWMB
    exit()
