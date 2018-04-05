package com.dev.sb;

import java.awt.image.BufferedImage;

public class ExtractText
    {
        public static StringBuilder extract(BufferedImage encryptedImage)
            {
                int x = 0, y = 0, bitValue;
                final int EXTRACTOR = 0x00000001;
                final int ONEATSTART=0x80;
                char chars;
                int asciiCode = 0;
                StringBuilder stringBuilder = new StringBuilder();

                for (int i = 0; ; i++)
                    {
                        for (int j = 0; j < 8; j++)
                            {
                                bitValue = encryptedImage.getRGB(x, y) & EXTRACTOR;//Extracts last bit from blue color.
                                x++;

                                if (x >= encryptedImage.getWidth())
                                    {
                                        x = 0;
                                        y++;

                                    }
                                asciiCode = asciiCode >> 1;//Left shift to form the character moving the bits by one place and store a new bit.
                                if (bitValue == 1)
                                    {
                                        asciiCode = asciiCode | ONEATSTART;//Replaces bit value with 1

                                    }
                            }
                        if (asciiCode == 0)//Checks for ascii value 1 marking end of the text.
                            {
                                break;
                            }
                        chars = (char) asciiCode;

                        stringBuilder.append(chars);//for appending characters at the end of the previous characters to form a string.
                    }

                return stringBuilder;
            }
    }
