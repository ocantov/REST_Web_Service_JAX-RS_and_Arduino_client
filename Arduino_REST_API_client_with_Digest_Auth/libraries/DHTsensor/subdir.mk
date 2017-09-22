################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../libraries/DHTsensor/DHT.cpp 

LINK_OBJ += \
./libraries/DHTsensor/DHT.cpp.o 

CPP_DEPS += \
./libraries/DHTsensor/DHT.cpp.d 


# Each subdirectory must supply rules for building sources it contributes
libraries/DHTsensor/DHT.cpp.o: ../libraries/DHTsensor/DHT.cpp
	@echo 'Building file: $<'
	@echo 'Starting C++ compile'
	"/opt/ArduinoEclipseIDE//arduinoPlugin/packages/arduino/tools/avr-gcc/4.9.2-atmel3.5.3-arduino2/bin/avr-g++" -c -g -Os -Wall -Wextra -std=gnu++11 -fpermissive -fno-exceptions -ffunction-sections -fdata-sections -fno-threadsafe-statics -flto -mmcu=atmega2560 -DF_CPU=16000000L -DARDUINO=10609 -DARDUINO_AVR_MEGA2560 -DARDUINO_ARCH_AVR   -I"/opt/ArduinoEclipseIDE/arduinoPlugin/packages/arduino/hardware/avr/1.6.17/cores/arduino" -I"/home/victor/arduino/Basic_tests/libraries/SimpleTimer" -I"/home/victor/arduino/Basic_tests/libraries/TimerOne" -I"/home/victor/arduino/Basic_tests/libraries/ArduinoJson" -I"/home/victor/arduino/Basic_tests/libraries/DHTsensor" -I"/home/victor/arduino/Basic_tests/libraries/RTClib" -I"/home/victor/arduino/Basic_tests/libraries/DS3231" -I/home/victor/arduino/libraries/MD5 -I"/opt/ArduinoEclipseIDE/arduinoPlugin/packages/arduino/hardware/avr/1.6.17/variants/mega" -I"/opt/ArduinoEclipseIDE/arduinoPlugin/packages/arduino/hardware/avr/1.6.17/libraries/SoftwareSerial" -I"/opt/ArduinoEclipseIDE/arduinoPlugin/packages/arduino/hardware/avr/1.6.17/libraries/SoftwareSerial/src" -I"/home/victor/arduino/Basic_tests/libraries/MD5" -I"/home/victor/arduino/Basic_tests/libraries/MemoryFree" -I"/opt/ArduinoEclipseIDE/arduinoPlugin/packages/arduino/hardware/avr/1.6.17/libraries/Wire" -I"/opt/ArduinoEclipseIDE/arduinoPlugin/packages/arduino/hardware/avr/1.6.17/libraries/Wire/src" -I"/opt/ArduinoEclipseIDE/arduinoPlugin/packages/arduino/hardware/avr/1.6.17/libraries/SPI" -I"/opt/ArduinoEclipseIDE/arduinoPlugin/packages/arduino/hardware/avr/1.6.17/libraries/SPI/src" -I"/opt/ArduinoEclipseIDE/arduinoPlugin/libraries/SD/1.1.1" -I"/opt/ArduinoEclipseIDE/arduinoPlugin/libraries/SD/1.1.1/src" -MMD -MP -MF"$(@:%.o=%.d)" -MT"$@" -D__IN_ECLIPSE__=1 -x c++ "$<"  -o  "$@"
	@echo 'Finished building: $<'
	@echo ' '


