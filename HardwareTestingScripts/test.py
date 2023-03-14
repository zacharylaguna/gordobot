import RPi.GPIO as GPIO
import pigpio
import time
import sys

servo = 2
pwm = pigpio.pi()
pwm.set_mode(servo, pigpio.OUTPUT)
pwm.set_PWM_frequency(servo, 50)

pwm.set_servo_pulsewidth(servo, 500)
time.sleep(2.0) # can't tell when servo is done moving
pwm.set_servo_pulsewidth(servo, 1140)
time.sleep(2.0) # can't tell when servo is done moving
pwm.set_servo_pulsewidth(servo, 1780)
time.sleep(2.0) # can't tell when servo is done moving
pwm.set_servo_pulsewidth(servo, 2440)
time.sleep(2.0) # can't tell when servo is done moving

pwm.set_servo_pulsewidth(servo, 500)
time.sleep(2.0)
#for i in range(10):
#	pwm.set_servo_pulsewidth(servo, 550)
#	time.sleep(.1) # can't tell when servo is done moving
#	pwm.set_servo_pulsewidth(servo, 500)
#	time.sleep(.1)
# turning off servo
pwm.set_PWM_dutycycle(servo, 0)
pwm.set_PWM_frequency(servo, 0 )
