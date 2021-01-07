package airline;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;



public class Table extends JFrame {
	private int screenWidth=1200;
	private int screenHeight=800;
	private int table_width = 1165;
	private int table_height = 300;
	private Font ft = new Font("한컴 소망 B", Font.PLAIN, 20);
	private Image background = new ImageIcon(Main.class.getResource("../Images/reservationBg2.jpg")).getImage();
	private JPanel jp;
	private String[] columnType = { "편명","항공사","출발지", "도착지", "출발일자", "출발시간", "도착일자", "도착시간" };
	private Controller ctr = new Controller();
	private DefaultTableModel model;
	


	Table(CurAccount ca) {
		jp = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				g.drawImage(background, 0, 0, screenWidth, screenHeight, this);
			}
		};
		jp.setLayout(null);
		jp.setBounds(0, 0, screenWidth, screenHeight);
		setSize(1216, 839);
		setResizable(false);
		setLayout(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
//
		model = new DefaultTableModel(ctr.getTableData(), columnType) {//테이블 내용 업데이트를 위한 객체 생성
			public boolean isCellEditable(int i, int c) {// 내용 수정 불가 세팅 (선택은 가능, 수정 불가)
				return false;
			}
		};
		JTable table = new JTable(model);
		table.setSize(new Dimension(table_width, table_height));
		table.setFillsViewportHeight(false);
		table.setAutoCreateRowSorter(true);
		table.getTableHeader().setReorderingAllowed(false); // 컬럼들 이동 불가
		table.getTableHeader().setResizingAllowed(false); // 컬럼 크기 조절 불가
		
//		DefaultTableCellRenderer cellrender = new DefaultTableCellRenderer();//셀 가운데 정렬위한 객체생성
//		cellrender.setHorizontalAlignment(SwingConstants.CENTER);//셀 가운데 정렬 설정
//		TableColumnModel tcm = table.getColumnModel();//컬럼 리모콘 객체 생성
//		for(int i=0;i<8;i++) {
//			tcm.getColumn(i).setCellRenderer(cellrender);//컬럼 리모콘을 이용한 가운데정렬 셋업
//		}
		
		JScrollPane scrollPane = new JScrollPane(table);//스크롤패널에 테이블 부착
		scrollPane.setSize(table_width, table_height);
		JPanel tablePn = new JPanel();
		tablePn.add(scrollPane);
		tablePn.setLayout(null);
		tablePn.setBounds(15, 100, table_width, table_height);
		
		JLabel departureLb = new JLabel("출발지   :");
		JLabel arrivalLb = new JLabel("도착지 :");
		JLabel departDateLb = new JLabel("출발일시 :");
		JLabel ticketCountLb = new JLabel("매수     :");
		JLabel departTimeLb = new JLabel("시 이후");
		JLabel airlineKE = new JLabel("KE = 대한항공");
		JLabel airlineOZ = new JLabel("OZ = 아시아나");
		JLabel airlineJE = new JLabel("JE = 제주항공");
		JLabel airlineLb = new JLabel("항공사 :");
		String greetName = ca.getName()+"님 반갑습니다.";// 접속자 이름 받아서 교체되는 방식
		JLabel greetLb = new JLabel(greetName);
		JComboBox departureCb = new JComboBox();
		JComboBox arrivalCb = new JComboBox();
		JComboBox departDateCb = new JComboBox();
		JComboBox departTimeCb = new JComboBox();
		JComboBox ticketCountCb = new JComboBox();
		JComboBox airlineCb = new JComboBox();
		JButton inquiry = new JButton("조회하기");
		JButton reservation = new JButton("카트에 담기"); //원래 '예매하기'였던 것
		JButton init = new JButton("초기화");
//		JButton checkRSV = new JButton("예매내역 조회하기");
		JButton editACTData = new JButton("뒤로");//'회원정보수정' 이었던 것.. 현재 뒤로가기 

		departureCb.addItem("");
		departureCb.addItem("인천");
		departureCb.addItem("뉴욕");
		departureCb.addItem("파리");
		departureCb.addItem("방콕");
		departureCb.addItem("괌");
		arrivalCb.addItem("");
		arrivalCb.addItem("인천");
		arrivalCb.addItem("뉴욕");
		arrivalCb.addItem("파리");
		arrivalCb.addItem("방콕");
		arrivalCb.addItem("괌");
		airlineCb.addItem("");
		airlineCb.addItem("KE");
		airlineCb.addItem("OZ");
		airlineCb.addItem("JE");
		
		departureCb.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}			
			@Override
			public void mousePressed(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
				departureCb.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				departureCb.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		arrivalCb.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}			
			@Override
			public void mousePressed(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
				arrivalCb.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				arrivalCb.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		airlineCb.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}			
			@Override
			public void mousePressed(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
				airlineCb.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				airlineCb.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		departTimeCb.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}			
			@Override
			public void mousePressed(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
				departTimeCb.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				departTimeCb.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseClicked(MouseEvent e) {}
		});
		departDateCb.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}			
			@Override
			public void mousePressed(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
				departDateCb.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				departDateCb.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		ticketCountCb.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			@Override
			public void mousePressed(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
				ticketCountCb.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				ticketCountCb.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");// 먼저 포맷 지정
		Calendar cal = Calendar.getInstance();// 캘린더 객체 생성
		for (int i = 1; i <= 7; i++) { // 일주일을 의미해서 1~7까지 반복되도록 했다.
			cal.add(Calendar.DAY_OF_MONTH, +1);
			departDateCb.addItem(format.format(cal.getTime()));
		}
		
		for (int i = 0; i < 24; i++) {// 몇 시 이후 콤보박스 추가
			if (i < 10 && i >= 0) {
				departTimeCb.addItem("0" + i);
			} else {
				departTimeCb.addItem(i);
			}
		}
		
		for (int i = 1; i < 100; i++) {
			ticketCountCb.addItem(i);
		}
		inquiry.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}			
			@Override
			public void mousePressed(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
				inquiry.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				inquiry.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseClicked(MouseEvent e) {}
		});
		inquiry.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] comboSrc=new String[4];
				comboSrc[0] = departureCb.getSelectedItem().toString();//선택된 출발지
				comboSrc[1] = arrivalCb.getSelectedItem().toString();//선택된 도착지
				comboSrc[2] = departDateCb.getSelectedItem().toString() + ":"
						+ departTimeCb.getSelectedItem().toString();//선택된 출발일자:시간
				comboSrc[3] = airlineCb.getSelectedItem().toString();//선택된 항공사
				
				model = new DefaultTableModel(ctr.searchTableData(comboSrc), columnType) {
					public boolean isCellEditable(int i, int c) {
						return false;
					}
				};
				table.setModel(model);
				table.repaint();
			}});
		init.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}			
			@Override
			public void mousePressed(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
				init.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				init.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		init.addActionListener(e -> {
			model = new DefaultTableModel(ctr.getTableData(), columnType) {
				public boolean isCellEditable(int i, int c) {
					return false;
				}
			};
			table.setModel(model);
			departureCb.setSelectedIndex(0);//콤보박스 버튼 초기화
			arrivalCb.setSelectedIndex(0);
			departDateCb.setSelectedIndex(0);
			departTimeCb.setSelectedIndex(0);
			ticketCountCb.setSelectedIndex(0);
			airlineCb.setSelectedIndex(0);
			table.repaint();
		});
		editACTData.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {
				editACTData.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				editACTData.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseClicked(MouseEvent e) {}
		});
		editACTData.addActionListener(e-> {//'회원정보수정'이었던것.. 현재 뒤로가기
			dispose();//프로세스는 종료하지않고 창만 종료함
		});
		reservation.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {
				reservation.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				reservation.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseClicked(MouseEvent e) {}
		});
		reservation.addActionListener(e-> {//'예매하기'였던 것의 카트에 담기 액션
			String[] cartSrc=new String[5];
			if(table.getSelectedRow()==-1) {
				JOptionPane.showMessageDialog(this, "스케쥴을 선택하십시오.","에러",JOptionPane.ERROR_MESSAGE);
			} else {
			cartSrc[0]=table.getValueAt(table.getSelectedRow(), 0).toString();//편명
			cartSrc[1]=table.getValueAt(table.getSelectedRow(), 2).toString();//출발지
			cartSrc[2]=table.getValueAt(table.getSelectedRow(), 3).toString();//도착지
			cartSrc[3]=table.getValueAt(table.getSelectedRow(), 4).toString()+":"//출발일자:시간 ex)2020/12/24:23:10
					+table.getValueAt(table.getSelectedRow(), 5).toString();
			cartSrc[4]=table.getValueAt(table.getSelectedRow(), 6).toString()+":"//도착일자:시간
					+table.getValueAt(table.getSelectedRow(), 7).toString();
			int count = (int) ticketCountCb.getSelectedItem(); //선택한 매수만큼 카트에 저장
			for(int j=0;j<count;j++) {
			ctr.insertCart(cartSrc);
			}
			JOptionPane.showMessageDialog(this, "카트에 해당 스케쥴을 "+count+"개 담았습니다.");
			}
		});
		
		airlineKE.setBounds(186,40,100,20);
		airlineOZ.setBounds(185,60,100,20);
		airlineJE.setBounds(188,80,100,20);
		greetLb.setBounds(900, 50, 300, 30);
		
		departureLb.setBounds(110, 450, 100, 30);
		departureCb.setBounds(220, 450, 200, 30);
		departDateLb.setBounds(110, 500, 100, 30);
		departDateCb.setBounds(220, 500, 200, 30);
		ticketCountLb.setBounds(110, 550, 100, 30);
		ticketCountCb.setBounds(220, 550, 100, 30);
		
		departTimeLb.setBounds(610, 500, 100, 30);
		departTimeCb.setBounds(500, 500, 100, 30);
		arrivalLb.setBounds(500, 450, 100, 30);
		arrivalCb.setBounds(600, 450, 200, 30);
		airlineLb.setBounds(500,550,100,30);
		airlineCb.setBounds(600,550,200,30);
		
//		checkRSV.setBounds(850, 700, 250, 50);

		inquiry.setBounds(110, 700, 200, 50);
		reservation.setBounds(360, 700, 200, 50);
		init.setBounds(610, 700, 200, 50);
		editACTData.setBounds(860, 700, 200, 50);
		

		
		departureLb.setFont(ft);
		arrivalLb.setFont(ft);
		departDateLb.setFont(ft);
		ticketCountLb.setFont(ft);
		departTimeLb.setFont(ft);
		greetLb.setFont(ft);
		departureCb.setFont(ft);
		arrivalCb.setFont(ft);
		departDateCb.setFont(ft);
		departTimeCb.setFont(ft);
		ticketCountCb.setFont(ft);
		inquiry.setFont(ft);
		reservation.setFont(ft);
		init.setFont(ft);
//		checkRSV.setFont(ft);
		editACTData.setFont(ft);
		airlineLb.setFont(ft);
		airlineCb.setFont(ft);
		airlineKE.setFont(new Font("한컴 소망 B", Font.PLAIN, 12));
		airlineOZ.setFont(new Font("한컴 소망 B", Font.PLAIN, 12));
		airlineJE.setFont(new Font("한컴 소망 B", Font.PLAIN, 12));
		
		jp.add(tablePn);
		jp.add(departureLb);
		jp.add(arrivalLb);
		jp.add(departDateLb);
		jp.add(ticketCountLb);
		jp.add(departTimeLb);
		jp.add(greetLb);
		jp.add(departureCb);
		jp.add(arrivalCb);
		jp.add(departDateCb);
		jp.add(departTimeCb);
		jp.add(ticketCountCb);
		jp.add(inquiry);
		jp.add(reservation);
		jp.add(init);
//		jp.add(checkRSV);
		jp.add(editACTData);
		jp.add(airlineKE);
		jp.add(airlineOZ);
		jp.add(airlineJE);
		jp.add(airlineLb);
		jp.add(airlineCb);

		add(jp);
		setVisible(true);
	}
}
