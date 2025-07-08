# CSN-Master
###### Computer Systems and Networks Master Assistant
###### Written by Ashar
###### December 15-30, 2025
<br>

This program has <b>$3$</b> tools.

A combination of UTF-8 Encoder, Binary/Denary/Hexadecimal Converter and BaseNConverter. View read me for full explanation. Used for my module ECS404 Computer Systems and Networks (Y1-SEMA 2024)

## UTF-8 Formatter
Converts Binary to UTF-8 Format. 
- The Binary Limit: 0<n<111101000010010000000, or 2 million in denary.
Each values must be:
- Binary: 0-1

The return is in hex format. For UTF-8
- 1 Byte Encoding: 0000 0000 0000 0000 0xxx xxxx -> 0x##
- 2 Byte Encoding: 0000 0000 0000 0yyy yyxx xxxx -> 0x## 0x##
- 3 Byte Encoding: 0000 0000 zzzz yyyy yyxx xxxx -> 0x## 0x## 0x##
- 4 Byte Encoding: 000u uuuu zzzz yyyy yyxx xxxx -> 0x## 0x## 0x## 0x##
  
Furthermore, the input should be of the format:
- 1 Byte Encoding: 0000 0000 0000 0000 0xxx xxxx
- 2 Byte Encoding: 0000 0000 0000 0yyy yyxx xxxx
- 3 Byte Encoding: 0000 0000 zzzz yyyy yyxx xxxx
- 4 Byte Encoding: 000u uuuu zzzz yyyy yyxx xxxx

  <br>

  ## BDH Formatter 
- Convert a Binary, Denary or Hex to all 3 formats. Values must be positive, between 0 - 2,000,000. So, for each n:
- Binary: 0<n<111101000010010000000
- Denary: 0<n<2,000,000
- Hexadecimal: 0x1E847F
Each values must be:
- Binary: 0-1
- Hex: 0-9, a-f, case is not sensitive
- Denary: Standard 0-9 format

  <br>

  ## BaseNConverter
Convert a positive integer between 0-2,000,000 into a different base, between 0-35. Encoded using 0-9 for first 10 values, and then a-z for the next 26.
