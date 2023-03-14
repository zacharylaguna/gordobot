import RPi.GPIO as GPIO
from g_pump import Pumps
from g_servo import TopServo
import sys

# ingredient - an integer value from 0 to 5
#   represents an ingredient in a given position
#   4 and 5 are reserved for the liquid ingredients
#   and calls the pumps
# weight - a float
#   represents the weight to be dispensed in grams
def executeStep(ingredient, weight):
    if(ingredient <= 3 and ingredient >= 0):
        ts = TopServo()
        ts.changeIngredient(ingredient)
        ts.dispenseIngredient(weight)
    elif(ingredient <= 5 or ingredient >= 4):
        p = Pumps()
        p.dispensePump(ingredient, weight)
    return

if(len(sys.argv) != 3):
    print("Invalid number of args\nusage: main_controller.py <integer between 0 and 5> <weight>")
    sys.exit()

executeStep(int(sys.argv[1]), int(sys.argv[2]))
GPIO.cleanup()
