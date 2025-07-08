//ashar adeel
//25 december 2024 - merry christmas
//version 2
//converts a binary number to utf-8 format

import java.util.Scanner;

class data{ //calculation class
    String inputValue;
    String outputValue;
    String displayOutputValue;
    boolean oneByte = false;
    boolean twoByte = false;
    boolean threeByte = false;
    boolean fourByte = false;
    boolean tooLarge = false;
    String byte1;
    String byte2;
    String byte3;
    String byte4;
}

class utf8formatter{
    public static void main(String [] param){
        data user = createUser(null,null,null);
        //welcome text
        welcomeText();
        String proceed = input("Would you like to use UTF-8 Formatter? (y/n)");
        while(!proceed.equalsIgnoreCase("y") && !proceed.equalsIgnoreCase("n")){ //if input invalid
            proceed = input("Please give a valid answer (y/n)");
        }
        while(proceed.equalsIgnoreCase("y")) {
            user = resetData(user); //reset input
            //TAKE INPUT
            String valueinput = input("Input Binary Value : ");
            while (!isBinary(valueinput)) {
                valueinput = input("Invalid Value, please input another: ");
            }
            //CONVERT BINARY TO UTF-8
            setInput(user, valueinput);
            utf8SizeIdentifier(user);
            String input = utfZeroPadder(getInput(user));
            setInput(user, input);
            encodeUTF8(user);
            convertToUTF8(user);
            System.out.println("UTF-8 Code: " + getDisplayOutput(user) + ". ");
            System.out.println("...");

            //TRACE
            System.out.println("TRACE SEGMENT: ");
            System.out.print("BYTE 1: 0x" + getByte1(user));
            System.out.print("\t- BYTE 2: 0x" + getByte2(user));
            System.out.print("\t- BYTE 3: 0x" + getByte3(user));
            System.out.print("\t- BYTE 4: 0x" + getByte4(user));
            //
            System.out.println("\n ...");
            proceed = input("Would you like to continue (y/n)");
            while(!proceed.equalsIgnoreCase("y") && !proceed.equalsIgnoreCase("n")){ //if input invalid
                proceed = input("Please give a valid answer (y/n)");
            }
        }
        if(proceed.equalsIgnoreCase("n")){
            System.out.println("...");
            System.out.println("Thank you for using UTF-8 formatter WBA");
        }
    }

    public static data resetData(data user){
        setTooLarge(user);
        setInput(user,null);
        setOutput(user,null);
        setDisplayOutput(user,null);
        setByte1(user,null);
        setByte2(user,null);
        setByte3(user,null);
        setByte4(user,null);
        return user;
    }
    //message and information for user
    public static void welcomeText(){ //yap
        System.out.println("UTF-8 Formatter.");
        System.out.println("Input a binary value, between 0<n<111101000010010000000 (2 million)");
        System.out.println("Output will be UTF-8 code point.");
        System.out.println("Data will not be saved.");
        System.out.println("Written by ashar");
        System.out.println("...");
        System.out.println("...");
    }

    //CONVERT DATA TO UTF8
    public static void convertToUTF8(data user){
        //retrieve input
        String input = getInput(user);
        String output = "";
        String displayOutput = "";
        String byteStatus = getByteStatus(user);
        String byte1 = getByte1(user);
        String byte2 = getByte2(user);
        String byte3 = getByte3(user);
        String byte4 = getByte4(user);

        if(byteStatus.equals("1")){
            byte1 = getByte1(user);

            byte1 = convertBinToDenary(byte1);
            byte1 = convertDenToHex(byte1);

            output = byte1;
            displayOutput = "0x" + byte1;

        }
        if(byteStatus.equals("2")){
            byte1 = getByte1(user);
            byte2 = getByte2(user);

            byte1 = convertBinToDenary(byte1);
            byte1 = convertDenToHex(byte1);
            byte2 = convertBinToDenary(byte2);
            byte2 = convertDenToHex(byte2);

            output = byte1 + byte2;
            displayOutput = "0x" + byte1 + " 0x" + byte2;
        }
        if(byteStatus.equals("3")){
            byte1 = getByte1(user);
            byte2 = getByte2(user);
            byte3 = getByte3(user);

            byte1 = convertBinToDenary(byte1);
            byte1 = convertDenToHex(byte1);
            byte2 = convertBinToDenary(byte2);
            byte2 = convertDenToHex(byte2);
            byte3 = convertBinToDenary(byte3);
            byte3 = convertDenToHex(byte3);

            output = byte1 + byte2 + byte3;
            displayOutput = "0x" + byte1 + " 0x" + byte2 + " 0x" + byte3;
        }
        if(byteStatus.equals("4")){
            byte1 = getByte1(user);
            byte2 = getByte2(user);
            byte3 = getByte3(user);
            byte4 = getByte4(user);

            byte1 = convertBinToDenary(byte1);
            byte1 = convertDenToHex(byte1);
            byte2 = convertBinToDenary(byte2);
            byte2 = convertDenToHex(byte2);
            byte3 = convertBinToDenary(byte3);
            byte3 = convertDenToHex(byte3);
            byte4 = convertBinToDenary(byte4);
            byte4 = convertDenToHex(byte4);

            output = byte1 + byte2 + byte3 + byte4;
            displayOutput = "0x" + byte1 + " 0x" + byte2 + " 0x" + byte3 + " 0x" + byte4;
        }
        setOutput(user,output);
        setDisplayOutput(user,displayOutput);
    }


    //fill final byte with 0s to be a whole 8-bit word
    public static String fillByte(String input, String conc) {
        int paddingLength = 8 - (conc.length() + input.length());
        String padding = ""; // Initialize the padding string

        // Create the padding string with a loop
        for (int i = 0; i < paddingLength; i++) {
            padding += "0";
        }

        // Combine the parts and return the result
        return conc + padding + input;
    }

    //encode utf-8
    public static void encodeUTF8(data user){
        String input = getInput(user);
        String output = "";
        String displayoutput = "displayText";
        String byteStatus = getByteStatus(user);

        if(byteStatus.equals("1")){ //1 byte format
            //conc value
            String conc = "0";
            //format
            output = conc + input;
            //set first byte
            setByte1(user,output);
        }
        else if(byteStatus.equals("2")){ //2 byte format
            //variables
            String[] firstByte = new String[5];
            String[] secondByte = new String[6];
            String concFirstByte = "110";
            String concSecondByte = "10";
            String byte1 = ""; // output byte 1
            String byte2 = ""; // output byte 2

            //concatanate
            for (int i = 0; i < 5 && i < input.length() ; i++) {
                firstByte[i] = String.valueOf(input.charAt(i));
            }
            byte1 = concFirstByte + buildOutput(firstByte);
            for (int i = 5; i < 11 && i < input.length(); i++) {
                secondByte[i - 5] = String.valueOf(input.charAt(i));
            }
            byte2 = buildOutput(secondByte);
            byte2 = fillByte(byte2,concSecondByte);

            //set
            setByte1(user,byte1);
            setByte2(user,byte2);
        }
        else if(byteStatus.equals("3")){ //3 byte
            //variables, same format jus more
            String[] firstByte = new String[4];
            String[] secondByte = new String[6];
            String[] thirdByte = new String[6];
            String concFirstByte = "1110";
            String concSecondByte = "10";
            String byte1 = ""; // output byte 1
            String byte2 = ""; // output byte 2
            String byte3 = ""; // output byte 3

            //concatanate
            for(int i = 0; i < 4 && i < input.length() ; i++){
                firstByte[i] = String.valueOf(input.charAt(i));
            }
            byte1 = concFirstByte + buildOutput(firstByte);
            for(int i = 4; i < 10 && i < input.length() ; i++){
                secondByte[i - 4] = String.valueOf(input.charAt(i));
            }
            byte2 = concSecondByte + buildOutput(secondByte);
            for (int i = 10; i < 16 && i < input.length() ; i++) {
                thirdByte[i - 10] = String.valueOf(input.charAt(i));
            }
            byte3 = buildOutput(thirdByte);
            byte3 = fillByte(byte3,concSecondByte);

            //set
            setByte1(user,byte1);
            setByte2(user,byte2);
            setByte3(user,byte3);
        }
        else if(byteStatus.equals("4")){ //4 byte format
            //declare variables
            String[] firstByte = new String[5];
            String[] secondByte = new String[6];
            String[] thirdByte = new String[6];
            String[] fourthByte = new String[6];
            String concFirstByte = "11110";
            String concSecondByte = "10";
            String byte1 = "";
            String byte2 = "";
            String byte3 = "";
            String byte4 = "";

            //concatanate
            for (int i = 0; i < 3 && i < input.length(); i++) {
                firstByte[i] = String.valueOf(input.charAt(i));
            }
            byte1 = concFirstByte + buildOutput(firstByte);

            for (int i = 3; i < 9 && i < input.length(); i++) {
                secondByte[i - 3] = String.valueOf(input.charAt(i));
            }
            byte2 = concSecondByte + buildOutput(secondByte);

            for (int i = 9; i < 15 && i < input.length(); i++) {
                thirdByte[i - 9] = String.valueOf(input.charAt(i));
            }
            byte3 = concSecondByte + buildOutput(thirdByte);

            for (int i = 15; i < 21 && i < input.length(); i++) {
                fourthByte[i - 15] = String.valueOf(input.charAt(i));
            }
            byte4 = buildOutput(fourthByte);
            byte4 = fillByte(byte4,concSecondByte);

            //set
            setByte1(user,byte1);
            setByte2(user,byte2);
            setByte3(user,byte3);
            setByte4(user,byte4);
        }
        else{
            //else if erronous or too large to format
            displayoutput = "erronous data";
            output = "0";
            setOutput(user,output);
            setDisplayOutput(user,displayoutput);
        }
    }

    //pad to fit utf format
    public static String utfZeroPadder(String input){
        String output = "";
        String padding = "";
        if(input.length() < 7){ //byte1 needs no padding.
            padding = "";
        }
        else if(input.length() > 7 && input.length() <= 11){ //byte2
            int paddingLength = 11 - input.length();
            padding = "";
            for(int i =0; i < paddingLength; i++){
                padding += "0";
            }
        }
        else if(input.length() > 11 && input.length() <= 16){ //byte3
            int paddingLength = 16 - input.length();
            padding = "";
            for(int i =0; i < paddingLength; i++){
                padding += "0";
            }
        }
        else if(input.length() > 16 && input.length() <= 21) { //byte4
            int paddingLength = 21 - input.length();
            padding = "";
            for(int i =0; i < paddingLength; i++){
                padding += "0";
            }
        }
        else{
            padding = "";
        }
        output = padding + input;
        return output;
    }

    //identify which type of format to encode for utf-8
    public static void utf8SizeIdentifier(data user){
        String input = getInput(user);

        if (input.length() <= 7) {
            setOneByte(user);
        } else if (input.length() <= 11) {
            setTwoByte(user);
        } else if (input.length() <= 16) {
            setThreeByte(user);
        } else if (input.length() <= 21) {
            setFourByte(user);
        } else {
            setTooLarge(user);
        }
    }


    //  //  //  /   //  /   /   /CONVERSION CALCULATORS
    //DENARY TO BINARY
    public static String convertDenToBinary(String input){ //den->binary
        //DECLARE VARIABLES
        final int base = 2;
        int inputValue = Integer.parseInt(input);

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
        return output;
    }
    //DENARY TO HEXADECIMAL
    public static String convertDenToHex(String input){ //den -> hex
        //DECLARE VARIABLES
        final int base = 16;
        int inputValue = Integer.parseInt(input);
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
        return output;
    }
    //HEXADECIMAL TO DENARY
    public static String convertHexToDen(String input) { //hex -> den
        final int base = 16;
        int length = input.length();
        int[] values = new int[length];
        int resultValue = 0;

        //CONVERT ALL VALUES
        for(int i = 0; i < length; i++){
            values[i] = Integer.parseInt(assignedValueToDen(String.valueOf(input.charAt(length - i - 1))));
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
        return String.valueOf(resultValue);
    }
    //BINARY TO DENARY
    public static String convertBinToDenary(String input){ //bin -> den
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

    //GATHER INPUTS GENERALISED METHODS
    public static String input(String text){
        System.out.println(text);
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        return answer;
    }

    //  //  //  //  /   /   /   //  /   / ADTS
    //create adt
    public static data createUser(String out, String in, String x){
        data user = new data();
        user.outputValue = out;
        user.inputValue = in;
        user.displayOutputValue = x;
        return user;
    }
    //GETTERS
    public static String getOutput(data d) {
        return d.outputValue;
    }
    public static String getInput(data d) {
        return d.inputValue;
    }
    public static String getDisplayOutput(data d) {
        return d.displayOutputValue;
    }
    public static String getByteStatus(data d) {
        if(d.oneByte == true){
            return "1";
        }
        else if(d.twoByte == true){
            return "2";
        }
        else if(d.threeByte == true){
            return "3";
        }
        else if(d.fourByte == true){
            return "4";
        }
        else if(d.tooLarge == true){
            return "5";
        }
        else {
            return "-1";
        }
    }
    public static String getByte1(data d){
        return d.byte1;
    }
    public static String getByte2(data d){
        return d.byte2;
    }
    public static String getByte3(data d){
        return d.byte3;
    }
    public static String getByte4(data d){
        return d.byte4;
    }
    //SETTERS
    public static void setOutput(data d, String outputValue) {
        d.outputValue = outputValue;
    }
    public static void setInput(data d, String inputValue) {
        d.inputValue = inputValue;
    }
    public static void setDisplayOutput(data d, String extra) {
        d.displayOutputValue = extra;
    }
    public static void setOneByte(data d) {
        d.oneByte = true;
        d.twoByte = false;
        d.threeByte = false;
        d.fourByte = false;
        d.tooLarge = false;
    }
    public static void setTwoByte(data d) {
        d.oneByte = false;
        d.twoByte = true;
        d.threeByte = false;
        d.fourByte = false;
        d.tooLarge = false;
    }
    public static void setThreeByte(data d) {
        d.oneByte = false;
        d.twoByte = false;
        d.threeByte = true;
        d.fourByte = false;
        d.tooLarge = false;
    }
    public static void setFourByte(data d) {
        d.oneByte = false;
        d.twoByte = false;
        d.threeByte = false;
        d.fourByte = true;
        d.tooLarge = false;
    }
    public static void setTooLarge(data d){
        d.oneByte = false;
        d.twoByte = false;
        d.threeByte = false;
        d.fourByte = false;
        d.tooLarge = true;
    }
    public static void setByte1(data d, String b){
        d.byte1 = b;
    }
    public static void setByte2(data d, String b){
        d.byte2 = b;
    }
    public static void setByte3(data d, String b){
        d.byte3 = b;
    }
    public static void setByte4(data d, String b){
        d.byte4 = b;
    }
}