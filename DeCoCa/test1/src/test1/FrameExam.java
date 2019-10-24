package test1;


import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
 
 
public class FrameExam extends JFrame
{
    JLabel lbl;
    JLabel la1,la2,la3,la4,la5,la6,la7,la8,la9,la10,la11,la12;
    JTextField tx1,tx2,tx3,tx4,tx5,tx6,tx7,tx8,tx9,tx10,tx11,tx12;
    
    JPanel statuspanel,panel;
    
    JTextArea content;
 
    public FrameExam()
    {
          super( "차량 status" );	
          // FlowLayout사용
          setLayout( new FlowLayout() );
          // Border로 영역 생성
          EtchedBorder eborder =  new EtchedBorder();
          // 레이블 생성     
          lbl = new JLabel( "                차량 상태입니다.              " );
          // 레이블에 영역 만들기
          lbl.setBorder(eborder);
          // 패널추가
          statuspanel = new JPanel();
          // 레이블 추가
          statuspanel.add(lbl);
          add( statuspanel );
          ////////////////////////////////////////////////
          // id패널과 pw 패널생성
          panel = new JPanel();
          
          la1 = new JLabel("배터리");
          la2 = new JLabel("핸들조향");
          la3 = new JLabel("속도");
          la4 = new JLabel("타이어 공기압 앞왼");
          la5 = new JLabel("타이어 공기압 앞오");
          la6 = new JLabel("타이어 공기압 뒷왼");
          la7 = new JLabel("타이어 공기업 뒷오");
          la8 = new JLabel("내부온도");
          la9 = new JLabel("문열림");
          la10 = new JLabel("안전벨트");
          la11 = new JLabel("브레이크");
          la12 = new JLabel("엔진");
          
          // id텍스트필드와 pw텍스트 필드 선언
          tx1 = new JTextField(20);
          tx2 = new JTextField(20);
          tx3 = new JTextField(20);
          tx4 = new JTextField(20);
          tx5 = new JTextField(20);
          tx6 = new JTextField(20);
          tx7 = new JTextField(20);
          tx8 = new JTextField(20);
          tx9 = new JTextField(20);
          tx10 = new JTextField(20);
          tx11 = new JTextField(20);
          tx12 = new JTextField(20);
          
          add(la1); add(tx1);
          add( la2 ); add( tx2 );
          add( la3 ); add( tx3 );
          add( la4 ); add( tx4 );
          add( la5 ); add( tx5 );
          add( la6); add( tx6 );
          add( la7); add( tx7 );
          add( la8 ); add( tx8 );
          add( la9 ); add( tx9 );
          add( la10 ); add( tx10 );
          add( la11 ); add( tx11 );
          add( la12 ); add( tx12 );
          
          

         
          setSize( 250, 700 );
          setVisible(true);
          setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public static void main( String args[] )
       { 
        new FrameExam();
       }
 
    
}