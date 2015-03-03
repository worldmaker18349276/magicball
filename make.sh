#!/bin/bash

C_OPTIONS=" \
-encoding utf-8 \
-d bin \
-sourcepath src"

RUN_OPTIONS="-cp ./bin"


case $1 in
	"build")
		shift
		case $1 in
			"")
				javac $C_OPTIONS @classes
				;;
			*)
				javac $C_OPTIONS $1
				;;
		esac
		;;

	"run")
		java $RUN_OPTIONS $2
		;;

	"clear")
		rm -r bin
		mkdir bin
		;;

	*)
		echo "Usage:"
		echo "  make build \$file  : compile specified file."
		echo "  make build        : compile all files in classes."
		echo "  make run \$class   : execute specified class."
		echo "  make clear        : clear bin."
		;;
esac
