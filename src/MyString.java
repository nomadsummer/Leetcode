/*
 * Author: Travis Cox
 * Program 1 - MyString
 * CSC230-02 Spring 2016
 */

public class MyString {
    //region Private Variables
        private char[] strChars;    // Array for storing the characters of the string.
        private int currLength;     // Variable for storing the length of the string
    //endregion

    //region Constructors
        // MyString(): Default Constructor, sets strChars to null and currLength to 0
        public MyString()
        {
            currLength = 0;
            ensureCapacity();
        }

        // MyString(String str): String Constructor, accepts a standard string and sets object to its value
        public MyString(String str)
        {
            // Set the length of MyString
            currLength = str.length();

            // Allocate space to array
            ensureCapacity();

            // Loop over str and set values of strChars
            for (int index = 0; index < currLength; index++)
            {
                strChars[index] = str.charAt(index);
            }
        }

        // MyString(MyString str): MyString constructor, accepts a MyString and copies its value
        public MyString(MyString str)
        {
            // Set the length of MyString
            currLength = str.length();

            // Allocate space to array
            ensureCapacity();

            // Loop over inputString and set values of strChars
            for (int index = 0; index < currLength; index++)
            {
                strChars[index] = str.get(index);
            }
        }
    //endregion

    //region Private Methods
        // ensureCapacity(): updates the capacity of the array to match currLength
        private void ensureCapacity()
        {
            // if currLength is 0 set strChars to null
            if (currLength == 0)
            {
                strChars = null;
            }
            else
            {
                // check if strChars is null, if so assign new array. Default null character will be in each index
                if (strChars == null)
                {
                    strChars = new char[currLength];
                }
                //only need to reallocate space if strChars.length is different than currLength
                else if (strChars.length != currLength)
                {
                    // create a new array that will replace strChars after being copied
                    char[] tempChars = new char[currLength];

                    // loop over tempChars and copy strChars, if strChars is longer,
                    // then data will be truncated, if strChars is shorter then additional
                    // characters will be set to ' '
                    for (int index = 0; index < currLength; index++) {
                        if (index < strChars.length) {
                            tempChars[index] = strChars[index];
                        } else {
                            tempChars[index] = ' ';
                        }
                    }
                    strChars = tempChars;
                }
            }
        }
    //endregion

    //region Public Methods
        // length(): returns the length of the MyString object
        public int length()
        {
            return currLength;
        }

        // toString(): returns the value of this MyString object as a string
        public String toString()
        {
            // If currLength is 0 return an empty string
            if (currLength == 0)
            {
                return "";
            }
            else
            {
                // Create the return string by passing strChars to the String constructor
                return new String(strChars);
            }
        }

        // concat(MyString str): returns a new MyString whose value is the concatenation of this MyString
        // and the parameter MyString
        public MyString concat(MyString str)
        {
            // use the concat(String) method to concatenate the two strings and return its result
            return this.concat(str.toString());
        }

        // concat(String str): returns a new MyString whose value is the concatenation of this MyString
        // and the parameter String
        public MyString concat(String str)
        {
            // create a new string that is the concatenation of this string and str
            String newString = this.toString() + str;

            // return new MyString using newString
            return  new MyString(newString);
        }

        // equals(MyString str): returns true if the value of this MyString and the parameter MyString
        // are the same returns false otherwise
        public boolean equals(MyString str)
        {
            // Use compareTo to check if the MyStrings are equal
            return compareTo(str) == 0;
        }

        // compareTo(MyString str): compares another MyString object to this one, returns 0 if the to MyString
        // objects have equal values, returns -1 if this MyString comes first alphabetically, returns 1 if this MyString
        // comes second alphabetically. Treats empty MyString as coming first.
        public int compareTo(MyString str)
        {
            // Declare and initialize the value of the comparison to 0
            int comparisonValue = 0;

            // Check if this MyString is empty
            if (currLength == 0)
            {   // If this MyString is length 0 and the input is not, then this MyString comes first
                if (str.length() > 0)
                {
                    comparisonValue = -1;
                }
            }
            // Check if input MyString is length 0, if so it comes before this MyString (since we have already verified
            // that this MyString.length is > 0)
            else if (str.length() == 0)
            {
                comparisonValue = 1;
            }
            // Both MyStrings have a length > 0
            else
            {
                // Get the value of the target MyString and store to an array
                char[] targetChars = str.toString().toCharArray();

                // iterate over the two arrays as long as index is smaller than both arrays and comparison value is 0, if
                // comparison value is not 0 then no need for further comparisons
                int index = 0;
                while (comparisonValue == 0 && index < strChars.length && index < targetChars.length)
                {
                    // Check if the character in this MyString comes before the character in the target MyString
                    if (strChars[index] < targetChars[index])
                    {
                        // If so set comparisonValue to -1
                        comparisonValue = -1;
                    }
                    // Check if the character in this MyString comes before the character in the target MyString
                    else if (strChars[index] > targetChars[index])
                    {
                        // If so set comparisonValue to 1
                        comparisonValue = 1;
                    }
                    index++;
                }

                // If the comparison value ends up 0 check if the arrays are the same size and if not treat the shorter one
                // as coming first alphabetically
                if (comparisonValue == 0)
                {
                    if (currLength < targetChars.length)
                    {
                        comparisonValue = -1;
                    }
                    else if (currLength > targetChars.length)
                    {
                        comparisonValue = 1;
                    }
                }
            }


            // return the value of the comparison
            return comparisonValue;
        }

        // get(int index): returns the character at the index position
        public char get(int index) throws IndexOutOfBoundsException
        {
            // Check if the index is valid, this will also catch scenarios where the array is null (currLength = 0)
            if (index < 0 || index >= currLength)
            {
                // return null character
                //return '\u0000';

                // Throw an exception if out of range.
                throw new IndexOutOfBoundsException("Index out of range.");
            }
            else
            {
                // return the character at the index
                return strChars[index];
            }

        }

        // toUpper(): returns a MyString that is converted to all upper case letters
        public MyString toUpper()
        {
            // If this MyString has a length of 0, return a empty MyString
            if (currLength == 0)
            {
                return new MyString();
            }
            else {
                // create array to hold new characters
                char[] upperCaseChars = new char[currLength];

                // iterate over strChars and copy elements to upperCaseChars convert lower case letters to upper case
                for (int index = 0; index < currLength; index++) {
                    // Check unicode value to see if character is a lower case letter
                    if (strChars[index] >= 97 && strChars[index] <= 122) {
                        // Subtract 32 from unicode value to convert to upper case
                        upperCaseChars[index] = (char) (strChars[index] - 32);
                    } else {
                        // Directly copy character if not a lower case letter
                        upperCaseChars[index] = strChars[index];
                    }
                }

                // Return new MyString object
                return new MyString(new String(upperCaseChars));
            }
        }

        // toLower(): returns a MyString that is converted to all lower case letters
        public MyString toLower()
        {
            // If this MyString has a length of 0, return a empty MyString
            if (currLength == 0)
            {
                return new MyString();
            }
            else {
                // create array to hold new characters
                char[] lowerCaseChars = new char[currLength];

                // iterate over strChars and copy elements to lowerCaseChars convert upper case letters to lower case
                for (int index = 0; index < currLength; index++) {
                    // Check unicode value to see if character is a upper case letter
                    if (strChars[index] >= 65 && strChars[index] <= 90) {
                        // Add 32 to unicode value to convert to lower case
                        lowerCaseChars[index] = (char) (strChars[index] + 32);
                    } else {
                        // Directly copy character if not a upper case letter
                        lowerCaseChars[index] = strChars[index];
                    }
                }

                // Return new MyString object
                return new MyString(new String(lowerCaseChars));
            }
        }

        // indexOf(MyString str): returns the startingIndex of the first occurrence of a
        // substring that matches str, returns -1 if no match is found
        public int indexOf(MyString str)
        {
            // declare and initialize startingIndex and index
            int startingIndex = -1;     // starting index of matching substring, default to -1
            int index = 0;              // index used for iterating strChars

            // iterate over strChars as long as index + str length is less than currLength and
            // startingIndex has not been found
            while (index + str.length() <= currLength && startingIndex == -1)
            {
                if (this.substring(index, index + str.length()).equals(str))
                {
                    startingIndex = index;
                }
                index++;
            }

            return startingIndex;
        }

        // indexOf(MyString str): returns the startingIndex of the last occurrence of a
        // substring that matches str, returns -1 if no match is found
        public int lastIndexOf(MyString str)
        {
            // declare and initialize startingIndex and index
            int startingIndex = -1;     // starting index of matching substring, default to -1
            int index = currLength - str.length();              // index used for iterating strChars

            // iterate over strChars as long as index + str length is less than currLength and
            // startingIndex has not been found
            while (index >= 0 && startingIndex == -1)
            {
                if (this.substring(index, index + str.length()).equals(str))
                {
                    startingIndex = index;
                }
                index--;
            }

            return startingIndex;
        }

        // substring(int start): returns a MyString object that is the substring that includes the character at
        // the start and all following characters. Returns default MyString if start is invalid.
        public MyString substring(int start)
        {
            // Check that the start is less than the length of this MyString and >= 0
            if (start < currLength && start >= 0)
            {
                // declare array to hold the characters of the substring
                char[] substringArray = new char[currLength - start];

                // loop over this MyString starting at start and copy values to substringArray
                for (int index = start; index < currLength; index++)
                {
                    substringArray[index - start] = strChars[index];
                }

                // create String from substringArray and use to create new MyString
                String substringString = new String(substringArray);
                return new MyString(substringString);
            }
            else
            {
                // return default MyString if start is invalid
                return new MyString();
            }
        }

        // substring(int start, int end): returns a MyString object that is the substring that includes
        // the character at the start and all following characters before the end. Returns default
        // MyString if either parameter is invalid.
        public MyString substring(int start, int end)
        {
            // Check that ending index is <= than currLength, that start is less than end
            // and that start is >= 0
            if (start < end && start >= 0 && end <= currLength)
            {
                // declare array to hold the characters of the substring
                char[] substringArray = new char[end - start];

                // loop over this MyString starting at start and stopping before end
                // and copy values to substringArray
                for (int index = start; index < end; index++)
                {
                    substringArray[index - start] = strChars[index];
                }

                // create String from substringArray and use to create new MyString
                String substringString = new String(substringArray);
                return new MyString(substringString);
            }
            else
            {
                // return default MyString if start is invalid
                return new MyString();
            }

        }

    //endregion
}
