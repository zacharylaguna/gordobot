# handler.py
# Handler between Flask server and servos
import time
from gtts import gTTS
import os
from maincontroller import *

def request_handler(data):
    instruction         = data["instruction"]
    destinationPosition = data["destinationPosition"]
    ingredient          = data["ingredient"]
    amount              = data["amount"]
    unit                = data["unit"]
    ifActionable        = data["ifActionable"]

    # Text to speech
    myobj = gTTS(text=instruction, lang='en', slow=False)
    myobj.save("instruction.mp3")
    os.system("mpg321 instruction.mp3")

    if (ifActionable == False):
        return

    # Convert to grams if not grams
    if (unit != 'grams'):

        # Convert to liters if not liters
        if (unit != 'liters'):

            # Convert tablespoons to liters
            if (unit == 'tablespoons'):
                amount = amount * 0.01479

            # Convert quarts to liters
            elif (unit == 'quarts'):
                amount = amount * 0.946

            # Convert fl ounces to liters
            elif (unit == 'ounces'):
                amount = amount * 0.0284

            # Convert pints to liters
            elif (unit == 'pints'):
                amount = amount * 0.473

            elif (unit == 'mL'):
                amount = amount * 0.001

            else:
                print("Unit type not converted to liters properly")

        # Convert liters to grams for water
        if (ingredient == 'water'):
            amount_grams = amount * 1000 #(1L water = 1000g)

        # Convert liters of salt to grams
        elif (ingredient == 'salt'):
            amount_grams = amount * 1250 #(1L salt = 1250g)

        else:
            print("Unit type not converted to grams properly")

    # Units already in grams
    else:
        amount_grams = amount

    print("Dispensing " + str(amount_grams) + " grams of " + ingredient)

    # Send data to servos
    executeStep(destinationPosition, amount_grams)

    # Wait for user to click done and receive another POST request
