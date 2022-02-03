import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Calculator extends Component implements ActionListener {
	
	JFrame frame;
	JTextField text;
	Graphics g;
	JButton[] numButtons;
	String[] inputs;
	String[] answer;
	JPanel numPanel;
	JPanel opPanel;
	JPanel opPanel2;
	JButton deciButton, parLButton, parRButton, equalButton, addButton, subButton, divButton, multButton, delButton, clearButton, percButton;
	private boolean parent = false;
	private boolean noParent = true;
	private boolean done = true;
	
Calculator() {
		
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420, 500);
		frame.setLayout(null);
		frame.setResizable(false);
		//frame.setForeground(Color.LIGHT_GRAY);
		
		text = new JTextField();
		text.setBounds(50, 25, 300, 50);
		text.setFont(new Font("DialogInput",Font.BOLD,30));
		text.setEditable(false);
		//text.setBackground(Color.LIGHT_GRAY);
		
		
		frame.add(text);
		frame.setLocationRelativeTo(null);
		
		numButtons = new JButton[10];
		
		opPanel2 = new JPanel();
		opPanel2.setBounds(270, 100, 80, 230);
		opPanel2.setLayout(new GridLayout(4,1,10,10));
		
		
		numPanel = new JPanel();
		numPanel.setBounds(50, 100, 210, 230);
		numPanel.setLayout(new GridLayout(4,3,10,10));
		
		for(int i = 0; i < 10; ++i)
		{
			numButtons[i] = new JButton(String.valueOf(i));
			numButtons[i].addActionListener(this);
			numButtons[i].setFont(new Font("DialogInput",Font.TYPE1_FONT,20));
			numButtons[i].setFocusable(false);
		}
		
		
		deciButton = new JButton(".");
		deciButton.setFocusable(false);
		deciButton.setFont(new Font("DialogInput",Font.BOLD,20));
		deciButton.addActionListener(this);
		parLButton = new JButton("(");
		parLButton.setFocusable(false);
		parLButton.addActionListener(this);
		parLButton.setFont(new Font("DialogInput",Font.BOLD,20));
		parRButton = new JButton(")");
		parRButton.setFocusable(false);
		parRButton.addActionListener(this);
		parRButton.setFont(new Font("DialogInput",Font.BOLD,20));
		addButton = new JButton("+");
		addButton.setFocusable(false);
		addButton.addActionListener(this);
		subButton = new JButton("-");
		subButton.setFont(new Font("DialogInput",Font.BOLD,20));
		subButton.setFocusable(false);
		subButton.addActionListener(this);
		multButton = new JButton("x");
		multButton.setFocusable(false);
		multButton.addActionListener(this);
		divButton = new JButton("/");
		divButton.setFont(new Font("DialogInput",Font.BOLD,20));
		divButton.setFocusable(false);
		divButton.addActionListener(this);
		equalButton = new JButton("=");
		equalButton.setFocusable(false);
		equalButton.setFont(new Font("DialogInput",Font.BOLD,20));
		equalButton.addActionListener(this);
		delButton = new JButton("Del");
		delButton.setFocusable(false);
		delButton.setFont(new Font("DialogInput",Font.BOLD,8));
		delButton.addActionListener(this);
		clearButton = new JButton("AC");
		clearButton.setFocusable(false);
		clearButton.setFont(new Font("DialogInput",Font.BOLD,10));
		clearButton.addActionListener(this);
		percButton = new JButton("%");
		percButton.setFocusable(false);
		percButton.setFont(new Font("DialogInput",Font.BOLD,20));
		percButton.addActionListener(this);
		
		numPanel.add(numButtons[7]);
		numPanel.add(numButtons[8]);
		numPanel.add(numButtons[9]);
		opPanel2.add(divButton);
		numPanel.add(numButtons[4]);
		numPanel.add(numButtons[5]);
		numPanel.add(numButtons[6]);
		opPanel2.add(multButton);
		numPanel.add(numButtons[1]);
		numPanel.add(numButtons[2]);
		numPanel.add(numButtons[3]);
		opPanel2.add(subButton);
		numPanel.add(deciButton);
		numPanel.add(numButtons[0]);
		numPanel.add(percButton);
		opPanel2.add(addButton);
		
		
		
		opPanel = new JPanel();
		opPanel.setBounds(50, 350, 300, 60);
		opPanel.setLayout(new GridLayout(1,5,10,10));
		
		opPanel.add(clearButton);
		opPanel.add(delButton);
		opPanel.add(parLButton);
		opPanel.add(parRButton);
		opPanel.add(equalButton);
		
		
		
		
		
		
		frame.add(opPanel2);
		frame.add(opPanel);
		frame.add(numPanel);
		frame.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		
		Calculator cal = new Calculator();
		// TODO Auto-generated method stub

	}
	
	private String[] toArray(String textfield) {
		String temp = textfield;
		String temp2;
		String temp3;
		String temp4;
		String[] operation;
		int arrayNum = 0;
		int numOps = 0;
		int numPar = 0;
		int stringPos = 0;
		boolean negFirst = false;
		parent = false;
		for(int i = 0; i < temp.length(); i++)
		{
			if((int)temp.charAt(i) == 120)
			{
				temp3 = temp.substring(0,i);
				temp4 = temp.substring(i+1,temp.length());
				temp = temp3.concat("*").concat(temp4);
			}
		}
		for(int i = 0; i < temp.length(); i++)
		{
			if((int)temp.charAt(i) > 36 && (int)temp.charAt(i) < 48 && (int)temp.charAt(i) != 46)
			{
				numOps++;
				if(temp.charAt(i) == '-' && i == 0)
					numOps--;
			}
		}
		for(int i = 1; i < temp.length()-1; i++)
		{
			if(temp.charAt(i) == '-' && (int)temp.charAt(i+1) > 47 && (int)temp.charAt(i+1) < 58 && (int)temp.charAt(i-1) > 36 && (int)temp.charAt(i-1) < 48 && (int)temp.charAt(i-1) != 41)
				numOps--;
		}
		for(int i = 0; i < temp.length(); i++)
		{
			if(temp.charAt(i) == '(')
			{
				numPar++;
				parent = true;
			}
		}
		operation = new String[2*numOps+1-numPar];
		if(temp.charAt(0) == '-' && (int)temp.charAt(1) > 47 && (int)temp.charAt(1) < 58)
		{
			for(int i = 1; i < temp.length(); i++)
			{
				if((int)temp.charAt(i) > 36 && (int)temp.charAt(i) < 48 && (int)temp.charAt(i) != 46)
				{
					negFirst = true;
					stringPos = i;
					break;
				}
					
			}
			operation[0] = temp.substring(0,stringPos);
			arrayNum++;
		} else {
			negFirst = true;
		}
		if(temp.charAt(0) == '(')
		{
			negFirst = true;
			if((int)temp.charAt(1) > 47 && (int)temp.charAt(1) < 58)
				negFirst = false;
			operation[0] = String.valueOf(temp.charAt(0));
			stringPos = 1;
			arrayNum++;
		}
		for(int i = stringPos; i < temp.length(); i++)
		{
			if(i != 0 && i != temp.length() - 1)
				if(temp.charAt(i) == '-' && (int)temp.charAt(i+1) > 47 && (int)temp.charAt(i+1) < 58 && (int)temp.charAt(i-1) > 36 && (int)temp.charAt(i-1) < 48 && (int)temp.charAt(i-1) != 41)
					continue;
			if((int)temp.charAt(i) > 36 && (int)temp.charAt(i) < 48 && (int)temp.charAt(i) != 46)
			{
				if(!((temp.charAt(0) == ('-') || temp.charAt(0) == ('(')) && negFirst))
				{
					if(!((int)temp.charAt(i-1) > 36 && (int)temp.charAt(i-1) < 48))
					{
						temp2 = temp.substring(stringPos,i);		
						operation[arrayNum] = temp2;
						arrayNum++;
					}
				}
				negFirst = false;
				operation[arrayNum] = String.valueOf(temp.charAt(i));
				arrayNum++;
				stringPos = i+1;
			}
			if(i == temp.length()-1 && (int)temp.charAt(i) > 47 && (int)temp.charAt(i) < 58) 
			{
				temp2 = temp.substring(stringPos,temp.length());
				operation[arrayNum] = temp2;
			}
			for(int k = 0; k < operation.length; k++)
				if(operation[k] == null)
					operation[k] = " ";
						
		}	
	
		
		return operation;
	}
	
	private String[] replace(String[] input, int pos) {
		String[] temp = input;
		
		temp[pos] = " ";
		
		return temp;
	}
	
	private int lookForR(String[] input, int what) {
		String[] temp = input;
		int target = what;
		int x = 0;
		for(int i = 0; i < temp.length; i++)
		{
			if(temp[i] == null)
			{
				x++;
				continue;
			}
			for(int k = 0; k < temp[i].length(); k++)
			{
				if((int)temp[i].charAt(k) == (target))
				{
					x++;
				}
			}
				
		}
		return x;
	}
	
	
	private String[] check(String[] input, int start, int end) {
		String[] temp = input;
		int x = 0;
		int y = 0;
		int hello = lookForR(temp, 32);
		boolean doneCalculating;

		do
		{
			doneCalculating = true;
			noParent = true;
			
			for(int i = start; i < end - (hello); i++)
			{
				
				if(temp[i].equals("(") || temp[i].equals(")"))
				{
					System.out.println("Error");
					noParent = false;
					break;
				}
			}
			for(int i = start; i < end - (hello); i++)
			{
				if(temp[i].equals("+") || temp[i].equals("-") || temp[i].equals("/") || temp[i].equals("*"))
				{
					System.out.println("Error2");
					doneCalculating = false;
					break;
				}
			}
			System.out.println("Error3");
			for (int i = 0; i < temp.length; i++) 
				System.out.print(temp[i]);
			if(!noParent)
			{
				temp = parent(temp,start,end);
				System.out.println("Error4");
			
			} else { 
				
				temp = check2(temp,start,end); System.out.println("Error5");
				temp = par(temp,start,end);
			}
			
		} while(!doneCalculating);
		
		if(temp[0].charAt(0) == '-' && temp[0].charAt(1) == '0')
			temp[0] = "0.0";
		
		
		return temp;
	}
	
	private String[] check2(String[] input, int begin, int finish) {
		String[] temp = input;
		int x = 0;
		int y = 0;
		int start = begin;
		int end = finish;
		boolean doneCalculating;
		noParent = true;

		do
		{
			x = 0;
			y = 0;
			doneCalculating = true;
			
			for(int i = start; i < end; i++)
			{
				if(temp[i].equals("*"))
				{
					x = i;
					break;
				}
					
				if(temp[i].equals("/"))
				{
					y = i;
					break;
				}
	
			}
			if(x > y)
			{
				for(int i = start; i < end; i++)
				{
					if(temp[i].equals("*"))
					{
						
						temp = mult((temp),i, end);
						end--;
						end--;
						break;
					}
				}
				for(int i = start; i < end; i++)
				{
					if(temp[i].equals("/"))
					{
						
						temp = div((temp),i,end);
						end--;
						end--;
						break;
					}
				}
			} else {
				for(int i = start; i < end; i++)
				{
					if(temp[i].equals("/"))
					{
						
						temp = div((temp),i,end);
						end--;
						end--;
						break;
					}
				}
				for(int i = start; i < end; i++)
				{
					if(temp[i].equals("*"))
					{
						
						temp = mult((temp),i,end);
						end--;
						end--;
						break;
					}
				}
				
			}
			x = 0;
			y = 0;
			for(int i = start; i < end; i++)
			{
				if(temp[i].equals("-"))
				{
					x = i;
					break;
				}
					
				if(temp[i].equals("+"))
				{
					y = i;
					break;
				}
	
			}
			if(x > y)
			{
				for(int i = start; i < end; i++)
				{
					if(temp[i].equals("-"))
					{
						
						temp = sub((temp),i,end);
						end--;
						end--;
						break;
					}
				}
				for(int i = start; i < end; i++)
				{
					if(temp[i].equals("+"))
					{
						
						temp = add((temp),i,end);
						end--;
						end--;
						break;
					}
				}
				
			} else {
				for(int i = start; i < end; i++)
				{
					if(temp[i].equals("+"))
					{
						temp = add((temp),i,end);
						end--;
						end--;
						break;
					}
				}
				for(int i = start; i < end; i++)
				{
					if(temp[i].equals("-"))
					{
						temp = sub((temp),i,end);
						end--;
						end--;
						break;
					}
				}
				
			}
			for(int i = start; i < end; i++)
			{
				if(temp[i].equals("*") || temp[i].equals("/") || temp[i].equals("+") || temp[i].equals("-")) 
						doneCalculating = false;
			}
		} while (!doneCalculating);
		
		for (int d = 0; d < temp.length; d++) 
			System.out.print(temp[d]);
		
		return temp;
	}

	private String[] parent(String[] input, int pos, int end) {
		String[] temp = input;
		int n = 0;
		int x = 0;
		int start = pos;
		int hello = lookForR(temp, 32);
		
		for(int i = start; i < end; i++)
			{
				if(temp[i].equals("("))
				{
					n = i;
					break;
				}
			}
			start = n+1;
	
		for(int k = 0; k >= 0; k++)
		{
			n = 0;
			x = 0;
			for(int i = start; i < end; i++)
			{
				if(temp[i].equals("("))
				{
					n = i;
					break;
				}
				if(temp[i].equals(")"))
				{
					x = i;
					break;
				}
			}
			if(n > x)
			{
				start = n+1;
				continue;
			} else {
				System.out.println(x + " " + start);
				
				temp = check2(temp,start,x-1);
				
				temp = par(temp,start-1,x+1);
				for (int d = 0; d < temp.length; d++) 
					System.out.print(temp[d]);
				break;
			}
		}
		
		return temp;
		
	}
	
	private String[] par(String[] input, int start, int end) {
		String[] temp = input;
		double num = 0;
		double num2 = 0;
		int numOfParL = 0;
		int numOfParR = 0;
		int numOfParLeft = 0;
		double result;
		int hello = lookForR(inputs, 32);
		
		for(int i = start; i < end; i++)
		{
			if(i != 0)
			{
				if(temp[i].equals("(") && (temp[i-1].equals("(") || temp[i-1].equals("+") || temp[i-1].equals("/") || temp[i-1].equals("*") || temp[i-1].equals("-")))
					{
						temp[i] = " ";
						end--;
						for(int k = i; k < temp.length - 1; k++)
						{	
							temp[k] = temp[k+1];
						}
					}	
			}
			
			if(i < temp.length-1)
				if(temp[i].equals(")") && (temp[i+1].equals(")") || temp[i+1].equals("+") || temp[i+1].equals("/") || temp[i+1].equals("-") || temp[i+1].equals("*") || temp[i+1].equals("(")))
				{
					temp[i] = " ";
					end--;
					System.out.println("");
					for (int d = 0; d < temp.length; d++) 
						System.out.println(temp[d]);
					for(int k = i; k < temp.length - 1; k++)
					{	
						temp[k] = temp[k+1];
					}
				}
		}
		
			for(int k = 0; k < temp.length - hello; k++)
			{
				if(temp[k].equals("("))
				{
					numOfParL++;
				}
			}
			for(int k = 0; k < temp.length - hello; k++)
			{
				if(temp[k].equals(")"))
				{
					numOfParR++;
				}
			}
			numOfParLeft =  numOfParL - numOfParR;
			
			if(numOfParLeft == -1)
				numOfParLeft = numOfParR;
			else if(numOfParLeft == 0)
					numOfParLeft = numOfParL;
				else numOfParLeft = 0;
			
			if(numOfParLeft == 1)
			{
				temp = check2(temp,start+1,end-1);
				if(temp[0].equals("(")) 
				{
					temp[0] = " ";
					for(int c = 0; c < temp.length - 1; c++)
					{	
						temp[c] = temp[c+1];
					}
				}
				if(temp[temp.length-(hello+1)].equals(")"))
					temp[temp.length-(hello+1)] = " ";
			}
			System.out.println("Here");
			for(int i = start; i < end; i++)
			{
				if(i == end-(hello+1) && temp[i].equals("("))
					break;
				if(temp[i].equals("(") || temp[i].equals(")"))
				{
					temp[i] = "&";
				}
				System.out.print(hello + " " + temp.length + " " +(temp.length-(hello+1)));
				
				if(temp[i].equals("&") && i == 0)
				{
					temp[i] = " ";
					for(int c = i; c < temp.length - 1; c++)
					{	
						temp[c] = temp[c+1];
					}
				}	
				if(temp[i].equals("&") && i == temp.length-(hello+1))
				{
					temp[i] = " ";
					for(int c = i; c < temp.length - 1; c++)
					{	
						temp[c] = temp[c+1];
					}
				}
				System.out.println("");
				for (int d = 0; d < temp.length; d++) 
					System.out.println(temp[d]);
				for(int k = start; k < end; k++)
					if(temp[k].equals("&") && (temp[k-1].equals("-")))
					{
						temp[k] = " ";
						for(int c = k; c < temp.length - 1; c++)
						{	
							temp[c] = temp[c+1];
						}
					}	
				if(temp[i].equals("&"))
				{
					num = Double.parseDouble(temp[i-1]);
					if(temp[i+1].equals(" "))
					{
						temp[i] = " ";
						break;
					}
					num2 = Double.parseDouble(temp[i+1]);
					result = num*num2;
					temp[i-1] = String.valueOf(result);
					
					for(int k = i; k < temp.length - 2; k++)
					{	
						temp[k] = temp[k+2];
					}
					
					for(int k = temp.length - 2; k < temp.length; k++)
						temp = replace(temp,k);

					break;
				}	
			}
			
		return temp;
	}
	
	private String[] mult(String[] input, int start, int end) {
		String[] temp = input;
		double num = 0;
		double num2 = 0;
		double result;
		int hello = lookForR(inputs, 32);
		for(int i = start; i < end; i++)
		{
			if(temp[i].equals("*"))
			{
				num = Double.parseDouble(temp[i-1]);
				num2 = Double.parseDouble(temp[i+1]);
				result = num*num2;
				temp[i-1] = String.valueOf(result);
				
				for(int k = i; k < temp.length - 2; k++)
				{	
					temp[k] = temp[k+2];
				}
				
				for(int k = temp.length - 2; k < temp.length; k++)
					temp = replace(temp,k);

				break;
			}	
		}
		return temp;
	}
	
	private String[] div(String[] input, int start, int end) {
		String[] temp = input;
		double num = 0;
		double num2 = 0;
		double result;
		int hello = lookForR(inputs, 32);
		for(int i = start; i < end; i++)
		{
			if(temp[i].equals("/"))
			{
				num = Double.parseDouble(temp[i-1]);
				if(num == 0)
				{
					temp[0] = "Undefined";
					for(int k = 1; k < temp.length; k++)
						temp = replace(temp,k);
					break;
				}
				num2 = Double.parseDouble(temp[i+1]);
				result = num/num2;
				temp[i-1] = String.valueOf(result);
				
				for(int k = i; k < temp.length - 2; k++)
				{	
					temp[k] = temp[k+2];
				}
				
				for(int k = temp.length - 2; k < temp.length; k++)
					temp = replace(temp,k);

				break;
			}	
		}
		return temp;
	}
	
	private String[] add(String[] input, int start, int end) {
		String[] temp = input;
		String temp2;
		String temp3;
		double num = 0;
		double num2 = 0;
		double result = 0;
		int hello = lookForR(inputs, 32);
		for(int i = start; i < end; i++)
		{
			if(temp[i].equals("+"))
			{
				num = Double.parseDouble(temp[i-1]);
				num2 = Double.parseDouble(temp[i+1]);
				result = num+num2;
				temp[i-1] = String.valueOf(result); 
				
				for(int k = i; k < temp.length - 2; k++)
				{	
					temp[k] = temp[k+2];
				}
				
				for(int k = temp.length - 2; k < temp.length; k++)
					temp = replace(temp,k);

				break;
			}
			
				
		}	
		
		
		return temp;
	}
	
	private String[] sub(String[] input, int start, int end) {
		String[] temp = input;
		double num = 0;
		double num2 = 0;
		double result;
		int hello = lookForR(inputs, 32);
		for(int i = start; i < end; i++)
		{
			if(temp[i].equals("-"))
			{
				for (int d = 0; d < temp.length; d++) 
					System.out.println(temp[d]);
				num = Double.parseDouble(temp[i-1]);
				num2 = Double.parseDouble(temp[i+1]);
				result = num-num2;
				temp[i-1] = String.valueOf(result);
				
				for(int k = i; k < temp.length - 2; k++)
				{	
					temp[k] = temp[k+2];
				}
				
				for(int k = temp.length - 2; k < temp.length; k++)
					temp = replace(temp,k);

				break;
			}	
		}
		
		return temp;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i = 0; i < 10; i++)
		{
			if(e.getSource() == numButtons[i]) {
				text.setText(text.getText().concat(String.valueOf(i)));
				
			}
		}
		if(e.getSource() == divButton) {
			text.setText(text.getText().concat("/"));
		}
		
		if(e.getSource() == multButton) {
			text.setText(text.getText().concat("x"));
		}
		if(e.getSource() == subButton) {
			text.setText(text.getText().concat("-"));
		}
		if(e.getSource() == addButton) {
			text.setText(text.getText().concat("+"));
		}
		if(e.getSource() == deciButton) {
			text.setText(text.getText().concat("."));
		}
		if(e.getSource() == parLButton) {
			text.setText(text.getText().concat("("));

		}
		if(e.getSource() == parRButton) {
			text.setText(text.getText().concat(")"));
		}

		if(e.getSource() == delButton) {
			String temp = text.getText();
			text.setText("");
			for(int i = 0; i < temp.length() - 1; i++) {
				text.setText(text.getText()+temp.charAt(i));
			}
			
		}
		if(e.getSource() == clearButton)
		{
			text.setText("");
		}
		
		if(e.getSource() == percButton) {
			//text.setText(text.getText().concat("%"));
		}
		
		if(e.getSource() == equalButton) {

			inputs = toArray(text.getText());
			for (int i = 0; i < inputs.length; i++) 
				System.out.println(inputs[i]);
			
			
			
			answer = check(inputs,0,inputs.length);

			//answer = par(inputs,0,inputs.length);
			
			text.setText(answer[0]);
			
			
			for (int i = 0; i < inputs.length; i++) 
				System.out.println(answer[i]);
			
			int hello = lookForR(inputs, 32);
			System.out.println(hello);
		}
		
	}
		
	

}
