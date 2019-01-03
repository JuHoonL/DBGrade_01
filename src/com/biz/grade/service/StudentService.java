package com.biz.grade.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.biz.grade.dao.StudentDAO;
import com.biz.grade.dao.StudentDAOImp;
import com.biz.grade.vo.StudentVO;

public class StudentService {

	Scanner scan;
	
	List<StudentVO> stdList;
	String strF;
	StudentDAO stdDao;
	
	public StudentService(String strF) {
		stdList = new ArrayList();
		this.strF = strF;
		scan = new Scanner(System.in);
		stdDao = new StudentDAOImp();
	}
	
	public void readStdInfo() {
		
		FileReader fr;
		BufferedReader br;
		
		try {
			fr = new FileReader(strF);
			br = new BufferedReader(fr);

			//파일이 많을수록 이 코드가 안정적			
			String readFile = new String();
			while((readFile = br.readLine()) != null) {
//			while(true) {
//				String readFile = br.readLine();
//				if(readFile == null) break;
				String[] splitF = readFile.split(":");
/*				StudentVO vo = new StudentVO();
				vo.setSt_num(splitF[0]);
				vo.setSt_name(splitF[1]);
				vo.setSt_tel(splitF[2]);
				vo.setSt_addr(splitF[3]);
*/				String st_num = splitF[0];
				st_num = "00000" + st_num;
				
				int sEnd = st_num.length();
				int sStart = sEnd - 5;
				st_num = st_num.substring(sStart, sEnd);
				
				
				StudentVO vo = new StudentVO(
						st_num, splitF[1], splitF[2], splitF[3]);
				stdList.add(vo);
			}
			br.close();
			fr.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void stdMenu() {
		
		while(true) {
			System.out.println("==============================================");
			System.out.println("학생정보 관리");
			System.out.println("----------------------------------------------");
			System.out.println("1. 추가     2. 수정     3. 삭제     4. 리스트보기     0. 종료");
			System.out.println("5. 파일로 부터 읽기     6. BULK DB UPDATE");
			System.out.println("----------------------------------------------");
			System.out.print("선택 >> ");
			String strM = scan.nextLine();
			
			int intM = Integer.valueOf(strM);
			if(intM == 0) return;
			if(intM == 1) this.insertStudent();
			if(intM == 2) this.updateStudent();
			if(intM == 3) this.deleteStudent();
			if(intM == 4) ;
			if(intM == 5) this.readStdInfo();
			if(intM == 6) this.insertBulkData();
			
		}
	}

	private void insertStudent() {
		// TODO Auto-generated method stub
		
	}
	
	private void updateStudent() {
		// TODO Auto-generated method stub
		
	}

	private void deleteStudent() {
		// TODO Auto-generated method stub
		
	}

	private void insertBulkData() {
		// TODO stdList에 담겨있는 많은(bulk)데이터를 DB에 insert 하기
		
		/*
		 * Dao 인터페이스에 정의하지 않은 메서드를 사용해서 Dao의 어떤 기능을 수행하려고 한다.
		 * 이 메서드는 표준 메서드(CRUD)에 해당하는 메서드가 아니어서 굳이 인터페이스에 정의하지
		 * 않아도 된다. 이럴때는 아래와 같은 cast코드로 작성을 한다.
		 */
//		//1번 사용법
//		StudentDAOImp impDao = (StudentDAOImp) stdDao;
//		impDao.insertBulk(stdList);
		
		//2번 사용법
		((StudentDAOImp)stdDao).insertBulk(stdList);
		
	}


}
