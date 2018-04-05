package com.dev.sb;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static com.dev.sb.ExtractText.extract;
import static com.dev.sb.EmbedText.embed;
import static com.dev.sb.SaveImage.saveImage;

public class Main
    {

        public static void main(String[] args)
            {
                BufferedImage originalImage = null;//Creates a buffered image object to store original image.
                Scanner s = new Scanner(System.in);//Creates a scanner object to obtain input from the user.
                String decision = null;//Variable to store user's decision to embed data or extract data.
                int flag = 0;
                System.out.println("Do you want to embed text or extract text from the image?[e/d]");
                while (flag == 0)
                    {
                        decision = s.nextLine();

                        if (decision.equals("e") | decision.equals("d"))
                            {
                                flag=1;
                                System.out.println("Enter path of image:");
                                try
                                    {
                                        originalImage = ImageIO.read(new File(s.nextLine())); //Reads the input image and stores in a buffered image object.
                                    } catch (IOException e)
                                    {
                                        System.out.println(e.getMessage());
                                    }

                                if (decision.equals("e"))
                                    {
                                        System.out.println("Enter text to be embedded in image:");
                                        String embedText = s.nextLine();//Obtains text to be embedded from the user.


                                        if (embedText.length() > (((originalImage.getWidth() * originalImage.getHeight()) - 32) / 8)-1)//Checks whether image size is large enough to store text provided.
                                            {
                                                //To store data, text length should be less than (number of pixels-32 /8)

                                                System.out.println("A bigger image is required to store this data!");

                                            } else
                                            {
                                                System.out.println("Embedding");
                                                BufferedImage encryptedImage = embed(originalImage, embedText);

                                                saveImage(encryptedImage);
                                            }
                                    } else
                                    {
                                        System.out.println("Extracting");

                                        StringBuilder hiddenText = extract(originalImage);
                                        System.out.println("Extracted Text:");

                                        System.out.println("\n" + hiddenText);
                                    }
                            } else
                            {
                                System.out.println("Kindly enter \'e\' for encryption or \'d\' for decryption");
                            }
                    }


            }
    }
