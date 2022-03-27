#!/bin/bash


PORT=8887
OPTION=$1
case "$OPTION" in
help|""|\?)
    echo " Usage:  ./clientScript [option]"

    echo "option is one of:"


    echo "    connectwallet   <blockchain> <publickey> <privatekey> "


    echo "Example: ./clientScript.sh connectwallet bsc 0x122313131231321 qwqeqwesadsadsadaskjf "
    echo "    help Prints this usage menu."
    echo ""
    ;;

    connectwallet)
        curl http://localhost:$PORT/main-control/wallet/connect/$2/public_key/$3/private_key/$4
    ;;

    *)
        echo Wrong option: $OPTION
        ./clientScript.sh help
    ;;


esac
