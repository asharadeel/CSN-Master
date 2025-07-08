//written by ashar adeel
//date: 23 december 2024
//version 4
//binaryhexdenary converter to and fro all 3 types, returning all 3 types as output command line version


import java.util.Scanner;

class dataBF{ //calculation class
    String inputValue;
    String outputValue;
    String extra;
    boolean binary = false;
    boolean hex = false;
    boolean denary = false;
}



class bdhformatter{ //bdh formattter
    public static void main(String [] p){ //main
        dataBF user = createUser(null,null,null);
        welcomeText();
        String askUse = input("Would you like to use BDH Formatter? (y/n)");
        while (!askUse.equalsIgnoreCase("y") && !askUse.equalsIgnoreCase("n")) { // If input is invalid
            askUse = input("Invalid input, choose between y/n.");
        }
        while (askUse.equalsIgnoreCase("y")) { // If yes, do this
            converter(user);
            askUse = input("Would you like to convert another value? (y/n)");
        }
        if (askUse.equalsIgnoreCase("n")) { // If no, print this and close
            System.out.println("Thank you for using BDH Formatter, WBA");
        }

    }

    //message and information for user
    public static void welcomeText(){ //yap
        System.out.println("BDH Formatter.");
        System.out.println("Values must be positive, between 0 - 2 million in denary or equivalent.");
        System.out.println("For the input n \n Hex: 0<n<0x1E847F \n Binary: 0<n<111101000010010000000 \n Denary: 0<n<2M");
        System.out.println("Outputs will be given in all 3 key formats, \n binary,  \n denary \n hex.");
        System.out.println("Data will not be saved.");
        System.out.println("Written by ashar");
        System.out.println("...");
        System.out.println("...");
    }

    //key converter tool
    public static void converter(dataBF user){ //super method
        selectMode(user);
        if(getBinaryStatus(user)){
            System.out.println("You have chosen Binary Input.");
        }
        else if(getHexStatus(user)){
            System.out.println("You have chosen Hex Input.");
        }
        else if(getDenaryStatus(user)){
            System.out.println("You have chosen Denary Input.");
        }
        System.out.println("...");
        takeValue(user);
        if(getBinaryStatus(user)){ //convert binary to all 3
            System.out.println("Your Binary Number = #" + getInput(user));
            System.out.println("Denary Value = " + convertBinToDenary(user));
            int denary = Integer.parseInt(convertBinToDenary(user));
            setInput(user, String.valueOf(denary));
            System.out.println("Hexadecimal Value = 0x" + convertDenToHex(user).toUpperCase());
        }
        else if(getHexStatus(user)){ //convert hex to all 3
            System.out.println("Your Hexadecimal Value = 0x" + getInput(user).toUpperCase());
            System.out.println("Denary Value = " + convertHexToDen(user));
            int denary = Integer.parseInt(convertHexToDen(user));
            setInput(user, String.valueOf(denary));
            System.out.println("Binary Value = #" + convertDenToBinary(user));

        }
        else if(getDenaryStatus(user)){ //convert denary to all 3
            System.out.println("Your Denary Value = " + getInput(user));
            System.out.println("Binary Value = #" + convertDenToBinary(user));
            System.out.println("Hex Value = 0x" + convertDenToHex(user).toUpperCase());
        }

    }

    //  //  //  /   //  /   /   /CONVERSION CALCULATORS
    //DENARY TO BINARY
    public static String convertDenToBinary(dataBF user){ //den->binary
        //DECLARE VARIABLES
        final int base = 2;
        int inputValue = Integer.parseInt(getInput(user));

        int pointer = inputValue / base;
        String[] values = new String[pointer + 1];

        //CONVERSION
        while (inputValue != 0) {
            int remainder = (inputValue % base);
            values[pointer] = assignedValue(remainder);

            inputValue = inputValue / base;
            pointer = pointer - 1;
        }

        //SET VALUES
        String output = buildOutput(values);
        setOutput(user,output);
        return output;
    }
    //DENARY TO HEXADECIMAL
    public static String convertDenToHex(dataBF user){ //den -> hex
        //DECLARE VARIABLES
        final int base = 16;
        int inputValue = Integer.parseInt(getInput(user));
        int pointer = inputValue / base;
        String[] values = new String[pointer + 1];

        //CONVERSION
        while (inputValue != 0) {
            int remainder = (inputValue % base);
            values[pointer] = assignedValue(remainder);
            inputValue = inputValue / base;
            pointer = pointer - 1;
        }

        //SET VALUES
        String output = buildOutput(values);
        setOutput(user,output);
        return output;
    }
    //HEXADECIMAL TO DENARY
    public static String convertHexToDen(dataBF user) { //hex -> den
        final int base = 16;
        int length = getInput(user).length();
        int[] values = new int[length];
        int resultValue = 0;

        //CONVERT ALL VALUES
        for(int i = 0; i < length; i++){
            values[i] = Integer.parseInt(assignedValueToDen(String.valueOf(getInput(user).charAt(length - i - 1))));
        }

        //CALCULATE EACH VALUE AND TURN TO RIGHT FORMAT
        for (int i = 0; i < length; i++) {
            int factor = 1;
            for (int j = 0; j < i; j++) {
                factor *= base;
            }
            int currentResultValue = values[i] * factor;
            resultValue += currentResultValue;
        }

        //SET VALUES
        setOutput(user,String.valueOf(resultValue));
        return String.valueOf(resultValue);
    }
    //BINARY TO DENARY
    public static String convertBinToDenary(dataBF user){ //bin -> den
        String input = String.valueOf(getInput(user));
        int length = input.length();
        int  resultValue = 0;
        int[] result = new int[length + 1];
        final int base = 2;

        //CONVERT ALL VALUES
        for(int i = 0; i < length; i++){
            result[i] = Integer.parseInt(assignedValueToDen(String.valueOf(input.charAt(length - i - 1))));
        }

        //CALCULATE EACH VALUE AND FORMAT A RESULT
        for (int i = 0; i < length; i++) {
            int factor = 1;
            for (int j = 0; j < i; j++) {
                factor *= base;
            }
            int currentResultValue = result[i] * factor;
            resultValue += currentResultValue;
        }

        //SET OUTPUTS
        setOutput(user,String.valueOf(resultValue));
        return String.valueOf(resultValue);
    }

    //BUILD FINAL RESULT for DENARY -> CONVERSIONS
    public static String buildOutput(String[] values) { //for den -> any value
        String result = "";
        for (String val : values) {
            if (val != null) {
                result += val;
            }
        }
        return result;
    }

    //get the values weight in denary
    public static String assignedValueToDen(String value){
        String assVal = "";
        int tempStore = 0;

        if (value.length() == 1 && value.charAt(0) >= '0' && value.charAt(0) <= '9') {
            tempStore = Integer.parseInt(value); //quick conv
        }
        else if (value.equalsIgnoreCase("a")) {
            tempStore = 10;
        } else if (value.equalsIgnoreCase("b")) {
            tempStore = 11;
        } else if (value.equalsIgnoreCase("c")) {
            tempStore = 12;
        } else if (value.equalsIgnoreCase("d")) {
            tempStore = 13;
        } else if (value.equalsIgnoreCase("e")) {
            tempStore = 14;
        } else if (value.equalsIgnoreCase("f")) {
            tempStore = 15;
        }
        assVal = String.valueOf(tempStore);
        return assVal;
    }

    //convert denary to encoded format
    public static String assignedValue(int value){
        String assVal = "";
        if(value>=0 && value <=9){
            assVal = String.valueOf(value);
        } else if(value == 10){
            assVal = "a";
        } else if(value == 11){
            assVal = "b";
        } else if(value == 12){
            assVal = "c";
        } else if(value == 13){
            assVal = "d";
        } else if(value == 14){
            assVal = "e";
        } else if(value == 15) {
            assVal = "f";
        }
        return assVal;
    }

    //GATHER INPUTS FROM USER
    public static void takeValue(dataBF user){
        String userValue = input("Insert Value: "); //POSITIVE ONLY!!!

        if(getBinaryStatus(user)) {
            while (!isBinary(userValue)) { //validation
                userValue = input("Please enter a valid Binary Value! Value must be 0<n<111101000010010000000.");
            }
        }
        if(getHexStatus(user)) {
            while(!isHex(userValue)){ //validation
                userValue = input("Please enter a valid Hex Value! Value must be 0x<n<0x1E8480.");
            }
        }
        if(getDenaryStatus(user)){
            while (!isDenary(userValue)) { //validation
                userValue = input("Please enter a valid denary number! Value must be 0<n<2M.");
            }
        }
        //set values
        setInput(user,userValue);
    }

    //  //  //  //  //              TYPE VALIDATIONS
    //IS INPUT BINARY CHECK
    public static boolean isBinary(String value){
        if (value == null || value.isEmpty()) {
            return false;
        }

        // Iterate through the string using an index
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            if (c != '0' && c != '1') {
                return false;
            }
        }

        //add maxima
        String maxBinary = "111101000010010000000";
        if (value.length() > maxBinary.length() || (value.length() == maxBinary.length() && value.compareTo(maxBinary) >= 0)) {
            return false;
        }
        return true;
    }

    //HEXADECIMAL CHECK
    public static boolean isHex(String value) {
        if (value == null || value.isEmpty()) {
            return false;
        }

        // Iterate through the string using an index
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);

            // Check if the character is not a valid hex digit
            if (!(Character.isDigit(c) || (c >= 'A' && c <= 'F') || (c >= 'a' && c <= 'f'))) {
                return false;
            }
        }

        //limit check
        String maxHex = "1E8480";
        value = value.toUpperCase();

        if (value.length() > maxHex.length() || (value.length() == maxHex.length() && value.compareTo(maxHex) >= 0)) {
            return false;
        }

        return true;
    }

    //CHECK IF INT
    public static boolean isDenary(String inputValue) {
        // Check if input is null or empty
        if (inputValue == null || inputValue.isEmpty())
        {
            return false;
        }

        // Check for the negative sign at the beginning
        int startIndex = 0;
        if (inputValue.charAt(0) == '-')
        {
            startIndex = 1;
        }

        // Check that every character after the negative sign (if present) is a digit
        for (int i = startIndex; i < inputValue.length(); i++) {
            if (!Character.isDigit(inputValue.charAt(i)))
            {
                return false;  // If any character is not a digit, return false
            }
        }

        // Check if the numeric value is within the range [0, 2000000)
        if (inputValue.length() > 7 || (inputValue.length() == 7 && inputValue.compareTo("2000000") >= 0)) {
            return false; // Too large for the range
        }

        return inputValue.length() > startIndex;  // Ensure that the string has at least one digit
    }


    //  //  //  /   /   /   /   /   //  select conversion mode
    public static void selectMode(dataBF user){
        String userValue = input("Choose Input Type: [1] Binary | [2] Denary | [3] Hex");
        while(!userValue.equals("1") && !userValue.equals("2") && !userValue.equals("3")){
            userValue = input("Please choose one of the options.");
        }
        if(userValue.equals("1")){

            setmodeBinary(user);
        }
        else if(userValue.equals("2")){
            setmodeDenary(user);
        }
        else if(userValue.equals("3")){
            setmodeHex(user);
        }
    }


    //GATHER INPUTS GENERALISED METHODS
    public static String input(String text){
        System.out.println(text);
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        return answer;
    }

    //  //  ADT METHODS
    //CREATE USER
    public static dataBF createUser(String out, String in, String x){
        dataBF user = new dataBF();
        user.outputValue = out;
        user.inputValue = in;
        user.extra = x;
        return user;
    }
    //GETTERS
    public static String getOutput(dataBF d) {
        return d.outputValue;
    }
    public static String getInput(dataBF d) {
        return d.inputValue;
    }
    public static String getExtra(dataBF d) {
        return d.extra;
    }
    public static boolean getBinaryStatus(dataBF d) {
        return d.binary;
    }
    public static boolean getHexStatus(dataBF d) {
        return d.hex;
    }
    public static boolean getDenaryStatus(dataBF d) {
        return d.denary;
    }
    //SETTERS
    public static void setOutput(dataBF d, String outputValue) {
        d.outputValue = outputValue;
    }
    public static void setInput(dataBF d, String inputValue) {
        d.inputValue = inputValue;
    }
    public static void setExtra(dataBF d, String extra) {
        d.extra = extra;
    }
    public static void setmodeBinary(dataBF d) {
        d.binary = true;
        d.hex = false;
        d.denary = false;
    }
    public static void setmodeDenary(dataBF d) {
        d.binary = false;
        d.hex = false;
        d.denary = true;
    }
    public static void setmodeHex(dataBF d) {
        d.binary = false;
        d.hex = true;
        d.denary = false;
    }

}