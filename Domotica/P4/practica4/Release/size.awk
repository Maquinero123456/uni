/^(\.text|\.data|\.bootloader)/ {arduino_size += $2 }
/^(\.data|\.bss|\.noinit)/ {arduino_data += $2 }
/^(\.eeprom)/ {arduino_eeprom += $2 }
END { print "\nSketch uses " arduino_size " bytes (" int(arduino_size/32256*100+0.5)  "%)  of program storage space. Maximum is " 32256 " bytes.\nGlobal variables use "arduino_data" bytes ("int(arduino_data/2048*100+0.5)"%) of dynamic memory, leaving "2048-arduino_data" bytes for local variables. Maximum is "2048" bytes.\n"}