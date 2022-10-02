#!/usr/bin/env python3

import math


def to_decimal(value:str, base:int):
    decimal = 0

    num_digits = len(value)
    i = 0
    while (i < num_digits):
        val = value[num_digits-i-1]

        if val >= '0' and val <= '9':
           val = ord(val) - ord('0')
        elif val >= 'A' and val <= 'F':
            val = ord(val) - ord('A') + 10
        else:
            raise Exception( "Digit out of range", val, ": ", base, " at digit: ", i )

        if val > base - 1:
            raise Exception( "val greater than base", val, ": ", base, " at digit: ", i )

        decimal += val * pow(base, i)
        i += 1
    return(decimal)



def from_decimal(decimal:int, base:int):
    lookup  = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A' , 'B', 'C', 'D', 'E', 'F']
    output  = ""
    quotient = decimal

    while(quotient > 0):
        remainder = quotient % base
        output = lookup[remainder] + output
        quotient = math.floor(quotient/base) # or quotient // base in python
    return output


to_inputs = [
    [ 'FF', 16 ],
    [ '111111', 2 ],
    [ '77', 8 ],
]

for (value, base) in to_inputs:
    print( [value, base, to_decimal(value, base) ] )

print('=' * 72)

from_inputs = [
    [ 255, 16 ],
    [ 63, 2 ],
    [ 63, 8 ],
]
for (decimal, base) in from_inputs:
    print( [decimal, base, from_decimal(decimal, base) ] )
