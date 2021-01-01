import java.time.LocalDate;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.ArrayList;
import java.util.Scanner;

public class FridgeMain {
   static Scanner sc = new Scanner(System.in);
   static ArrayList<String[]> list = new ArrayList<String[]>();

   public static void main(String[] args) {

      System.out.println("\t\t\t..*..*..*..냉 장 고 를 부 탁 해..*..*..*..");
      System.out.println("======================================================================================");

      boolean boo = true;
      while (boo) {
         System.out.println();
         System.out.println("\t ①보여줘! 나의 냉장고  ②채우기  ③비우기  ④유통기한 확인  ⓞ종료  >>   ");
         int input = sc.nextInt();

         switch (input) {
         case 1:
            getItemList();
            break;
         case 2:
            setItem();
            break;
         case 3:
            remove();
            break;
         case 4:
            checkExpDate();
            break;
         case 0:
            boo = false;
            System.out.println("\n\t\t\t(ლ   ╹ ◡ ╹ )ლ     감 사 합 니 다  ლ( ╹ ◡ ╹ ლ)");
            break;

         }// end switch
      }// end while
   }// end main
 public static void getItemList() {
      System.out.println("--------------------------------------------------------------------------------------");
      System.out.println("번호\t|\t식품명\t|\t수량\t|\t유통기한\t|\t보관위치\t");
      System.out.println("--------------------------------------------------------------------------------------");
      for (String[] data : list) {    // data배열에 담긴 데이터를 리스트 사이즈까지 뽑는다.
         System.out.print(list.indexOf(data) + 1);
         System.out.println("\t" + "\t" + data[0] + "\t" + "\t" + data[1]+ "\t" + "\t" + data[2] + "\t" + "\t" + data[3]);
      }
   }

   public static void setItem() {
      System.out.println("\t ①새로 채우기 ②수정하기 >>");
      int addItem = sc.nextInt();

      if (addItem == 1) {
         String newItem = "";
         boolean bool = true;

         while (bool) {
            System.out.println("식품명 >> ");
            newItem = sc.next();

            int cnt = 0;
            for (int t = 0; t < list.size(); t++) {
               String oldItem = list.get(t)[0];
               if (oldItem.equals(newItem)) {
                  System.out.println("이미 등록된 상품이 있습니다.");
                  cnt = 0;
                  break;
               } else {
                  cnt++;
               }
            }
            if (cnt > 0 || list.size() == 0) {
               bool = false;
            }
         }
         System.out.println("수량 >> ");
         String ea = sc.next();
         String exp = "";
         boolean bo = true;
         while (bo) {
            System.out.println("유통기한 >> ex.yyyy-mm-dd");
            exp = sc.next();
            if (exp.length() == 10) {
               bo = false;
            } else if (exp.length() != 10) {
               System.out.println("형식에 맞춰 입력해주세요!");
            }
         }

         System.out.println("보관위치 >> ");
         String place = sc.next();

         list.add(new String[] { newItem, ea, exp, place });
         
      } else if (addItem == 2) {

         updateItem();
      }
   }   //set end
   
   public static void updateItem() {
      System.out.println("수정하실 품목이름을 입력해주세요 >> ");
      String reItem = sc.next();
      for (int t = 0; t < list.size(); t++) {
         String oldItem = list.get(t)[0];
         if (reItem.equals(oldItem)) {
            System.out.println("수정하실 항목을 선택해주세요 >> ");
            System.out.println("①식품명  ②수량  ③유통기한  ④보관위치 >>");
            int idx = sc.nextInt();
            System.out.println("수정내용을 입력해주세요 >> ");
            String update = sc.next();
            list.get(t)[idx - 1] = update;
            System.out.println("변경되었습니다.");

         }
      }// end for
   }// end update
   
   
   public static void remove() {
      System.out.println("냉장고에서 비울 식품 이름을 입력해주세요 >> ");
      String del = sc.next();

      for (int t = 0; t < list.size(); t++) {
         String item = list.get(t)[0];
         if (del.equals(item)) {
            list.remove(t);
         }
      }// end for
   }// remove end


   public static void checkExpDate() {
      LocalDate today = LocalDate.now();
      int month2 = today.getMonthValue();
      int year2 = today.getYear();
      int day2 = today.getDayOfMonth();

      LocalDate todayDate = LocalDate.of(year2, month2, day2);

      System.out.println("\t\t..*..*..*..냉 장 고 를 부 탁 해..*..*..*..");
      System.out.println("-----------------------------------------------------------------------------");
      System.out.println("\t식품명\t|\t수량\t|\t\t   유통기한\t");
      System.out.println("-----------------------------------------------------------------------------");

      for (int t = 0; t < list.size(); t++) {
         String[] items = new String[] {};
         String oldExp = list.get(t)[2]; // 기존의 유통기한을 불러오는 인덱스번호
         String[] expArr = oldExp.split("-");
         items = list.get(t);
         int year = Integer.parseInt(expArr[0]);
         int month = Integer.parseInt(expArr[1]);
         int day = Integer.parseInt(expArr[2]);
         LocalDate expDate = LocalDate.of(year, month, day);

         Period pe = Period.between(todayDate, expDate);
         int days = pe.getDays(); // 일수 차이
         int months = pe.getMonths(); // 개월 차이
         int years = pe.getYears();
         System.out.print("\t" + items[0] + "\t" + "\t" + items[1] + "\t\t" + items[2] + "     ");

         if (days < 0) {
            System.out.println("유통기한이 지났습니다.");
         } else if (months <= 0 && days > 0) {
            System.out.println(days + "일 남았습니다.");

         } else if (months > 0 && days == 0) {
            System.out.println(months + "개월 남았습니다.");

         } else if (years <= 0 && months > 0 && days > 0) {
            System.out.println(months + "개월 " + days + "일 남았습니다.");
         } else if (years > 0) {
            System.out.println(years + "년 이상 남았습니다.");
         }

      }// end for
   }



}// end class