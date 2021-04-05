import java.awt.*;
import java.awt.event.*;
import java.io.*;
class NotePad extends WindowAdapter implements ActionListener
{   Frame fr;
    MenuBar mb;
    Menu MenuContent[]=new Menu[5];
    MenuItem mi_for_File[]=new MenuItem[8];
    MenuItem mi_for_Edit[]=new MenuItem[13];
    MenuItem mi_for_Format[]=new MenuItem[2];
    MenuItem mi_for_View[]=new MenuItem[2];
    MenuItem mi_for_Help[]=new MenuItem[3];
    TextArea txt;
    Font ft;
    String fp;
    String MenuContainer[]={"File","Edit","Format","View","Help"};
    String FileContainer[]={"New","New Window","Open","Save","Save As...","Page Setup","Print","Exit"};
    String EditContainer[]={"Undo","Cut","Copy","Paste","Delete","Search","Find","Find Next","Find Previous","Replace","Go to..","Select All","Time/Date"};
    String FormatContainer[]={"Word Wrap","Font..."};
    String ViewContainer[]={"Zoom","Status Bar"};
    String HelpContainer[]={"View Help","Send Feedback","About Notepad"};
    NotePad()
    {
        fr=new Frame();
        fr.setTitle("Untitled-Notepad");
        mb=new MenuBar();
        fr.setMenuBar(mb);
        
        //Setting Menu Container Elements
        for(int i=0;i<MenuContainer.length;i++)
        {   
            MenuContent[i]=new Menu(MenuContainer[i]);
            mb.add(MenuContent[i]);
        }
        //Setting File Container Elements
        for(int i=0;i<FileContainer.length;i++)
        {   
            if(i==5 || i ==7){MenuContent[0].addSeparator();}//Separate by line
            mi_for_File[i]=new MenuItem(FileContainer[i]);
            MenuContent[0].add(mi_for_File[i]);
            mi_for_File[i].addActionListener(this);
        }
        //Setting Edit Container Elements
        for(int i=0;i<EditContainer.length;i++)
        {   
            if(i==1 || i ==5 || i==11){MenuContent[1].addSeparator();}//Separate by line
            mi_for_Edit[i]=new MenuItem(EditContainer[i]);
            MenuContent[1].add(mi_for_Edit[i]);
            mi_for_Edit[i].addActionListener(this);
        }
        //Setting Format Container Elements
        for(int i=0;i<FormatContainer.length;i++)
        {
            mi_for_Format[i]=new MenuItem(FormatContainer[i]);
            MenuContent[2].add(mi_for_Format[i]);
            mi_for_Format[i].addActionListener(this);
        }
        //Setting View Container Elements
        for(int i=0;i<ViewContainer.length;i++)
        {
            mi_for_View[i]=new MenuItem(ViewContainer[i]);
            MenuContent[3].add(mi_for_View[i]);
            mi_for_View[i].addActionListener(this);
        }
        //Setting Help Container Elements
        for(int i=0;i<HelpContainer.length;i++)
        {   
            if(i==2){MenuContent[4].addSeparator();}//Separate by line
            mi_for_Help[i]=new MenuItem(HelpContainer[i]);
            MenuContent[4].add(mi_for_Help[i]);
            mi_for_Help[i].addActionListener(this);
        }
        //END Of MenuBar
        
        //Setting TextArea
        txt=new TextArea();
        //txt.setMargins(0,0,0,0);
        fr.add(txt);
        
        //Setting up fontStyle and Adding To TextArea
        ft=new Font("Arial",Font.PLAIN,20);
        txt.setFont(ft);
        
        fr.addWindowListener(this);//to close the window
        fr.setVisible(true);
        fr.setSize(900,500);
        fr.setLocationRelativeTo(null);
    }
    public void windowClosing(WindowEvent e)
    {
        fr.dispose();
    }
    public void actionPerformed(ActionEvent ae)
    {
      try
      {
            String operator=ae.getActionCommand();
            if(operator.equals("New"))
            {
                txt.setText("");
                fr.setTitle("Untitled-Notepad");
            }
            if(operator.equals("New Window"))
            {
                new NotePad();
            }
            if(operator.equals("Open"))
            {   
                FileDialog fd=new FileDialog(fr);
                fd.show();
                fp=fd.getDirectory()+fd.getFile();
                if(fd.getFile()!=null)
                {
                    txt.setText("");
                    RandomAccessFile raf=new RandomAccessFile(fp,"r");
                    String data=raf.readLine();
                    while(data!=null)
                    {
                        txt.append(data+"\n");
                        data=raf.readLine();
                    }
                    fr.setTitle(fd.getFile()+"-Notepad");
                }
            }
            if(operator.equals("Save"))
            {   
                if(fr.getTitle().equals("Untitled-Notepad"))//FOR NEW FILE
                {
                FileDialog fd=new FileDialog(fr);
                fd.setMode(FileDialog.SAVE);
                fd.show();
                fp=fd.getDirectory()+fd.getFile();
                FileOutputStream fos=new FileOutputStream(fp);
                String data=txt.getText();
                fos.write(data.getBytes());
                fr.setTitle(fd.getFile()+"-Notepad");
                
                }
                else//FOR EXISTING FILE
                {
                FileOutputStream fos=new FileOutputStream(fp);
                String data=txt.getText();
                fos.write(data.getBytes());
                }
            }
            if(operator.equals("Save As..."))//FOR NEW FILE
            {
                FileDialog fd=new FileDialog(fr);
                fd.setMode(FileDialog.SAVE);
                fd.show();
                fp=fd.getDirectory()+fd.getFile();
                FileOutputStream fos=new FileOutputStream(fp);
                String data=txt.getText();
                fos.write(data.getBytes());
                fr.setTitle(fd.getFile()+"-Notepad");
            }
            if(operator.equals("Exit"))
            {
                System.exit(0);
            }
      }//end of try block
      catch(Exception e)
      {
      }
   }
   public static void main(String args[])
   {
        new NotePad();
   }
}
