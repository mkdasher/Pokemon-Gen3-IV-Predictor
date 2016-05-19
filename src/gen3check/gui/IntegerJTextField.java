package gen3check.gui;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

@SuppressWarnings("serial")
public class IntegerJTextField extends JTextField{
	public IntegerJTextField(){
		super();
		this.setDocument(new TextFieldLimiter(256));
	}
	
	public IntegerJTextField(int maxLength) {
		super();
		this.setDocument(new TextFieldLimiter(maxLength));
	}
	
	public boolean isEmpty(){
		return (this.getText().equals(""));
	}
	
	private class TextFieldLimiter extends PlainDocument
	{
	  int maxChar = -1;
	  public TextFieldLimiter(int len){maxChar = len;}
	  
	  public void insertString(int offs, String str, AttributeSet a) throws BadLocationException
	  {
		//MAX LENGTH LIMIT
	    if (str != null && maxChar > 0 && this.getLength() + str.length() > maxChar)
	    {
	      java.awt.Toolkit.getDefaultToolkit().beep();
	      return;
	    }
	    //NUMBER-ONLY LIMIT
	    else if (!isNumberString(str)){
	      java.awt.Toolkit.getDefaultToolkit().beep();
		  return;
	    }
	    super.insertString(offs, str, a);
	  }
	  
	  public boolean isNumberString(String str){
		  for (int i = 0; i < str.length(); i++){
			  if (!Character.isDigit(str.charAt(i))){
				  return false;
			  }
		  }
		  return true;
	  }
	}
}
