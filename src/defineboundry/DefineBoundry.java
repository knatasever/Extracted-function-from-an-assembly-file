/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creategraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author KÜBRA NUR
 */
public class CreateGraph {

    private static void defineBoundry(Scanner scanner, Pattern start_pattern, Pattern end_pattern, Pattern function_pattern, HashMap<String,ArrayList<String>> functionMap, 
            funQueue queue) 
            
	{                        ArrayList<String> array_op = new ArrayList<>();

		while(scanner.hasNext())
		{
			ArrayList<String> functionDataBuffer = new ArrayList<String>();
			String currentLine1 = scanner.nextLine();
                        
                        Matcher start_matcher = start_pattern.matcher(currentLine1);
			/* This if match will identify start of subroutine in assembly code */
			if(start_matcher.find())
			{
				String [] array = start_matcher.group().split(" ");
				String currentLine = scanner.nextLine();
				functionDataBuffer.add(currentLine);
				Matcher end_matcher = end_pattern.matcher(currentLine);
				/*Here while loop print everything within subroutine until it reached end of 
				 * subroutine
				 */
				while(!end_matcher.find())
				{
					currentLine = scanner.nextLine();
					end_matcher = end_pattern.matcher(currentLine);
					/* This if identifies end of subroutine */
					if(end_matcher.find())
					{
						functionMap.put(array[0], functionDataBuffer);

						break;
					}
					else
					{
						functionDataBuffer.add(currentLine);
					}	
				}
}
			else
			{
				Matcher function_matcher = function_pattern.matcher(currentLine1);
				boolean queueFlag=false;
				if(function_matcher.find())
				{
					String [] array = function_matcher.group().split(" ");
					for(int i=0;i<queue.length();i++)
					{
						if(queue.getQueue().get(i).equals(array[1]))
						{
							queueFlag=true;
							break;
						}
					}
					if(queueFlag==false)
						queue.insert(array[1]);
				}
			}
			} 

		for(Map.Entry<String, ArrayList<String>> entry:functionMap.entrySet())
		{
			String funName = entry.getKey();
			boolean isEntryFun = true;
			String instruction=null;
			String [] array = new String[2];
                        int xx=0;
			for(Map.Entry<String, ArrayList<String>> entry1:functionMap.entrySet())
			{	
				if(!entry1.getKey().equals(funName))
				{
					ArrayList<String> funData = entry1.getValue();
					for(int i=0;i<funData.size();i++)
					{
						instruction = funData.get(i);
						array = instruction.split(" ");
						if(array[0].equals("call"))
						{
							if(array[1].equals(funName))
							{
								isEntryFun = false;
								break;
							}
                                                }
                                               
					}
					array_op.add(array[0]);

					if(isEntryFun == false)
						break;	
				}
                                xx++;
			}
			if(isEntryFun == true)
			{
				boolean queueFlag=false;
				for(int i=0;i<queue.length();i++)
				{
					if(queue.getQueue().get(i).equals(funName))
					{
						queueFlag=true;
						break;
					}
				}
				if(queueFlag==false)
					queue.insert(funName);
			}
                                       

		}	              
        }
}
