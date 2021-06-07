package hotel.app;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import hotel.db.Khach_db;
import hotel.oop.Khach;

public class Khach_ap 
{
	private static Scanner sc = new Scanner(System.in);
	
	//Them moi khach hang:
	public static void nhapThongTinKhach() {
		System.out.println("======Nhap thong tin Khach muon thue phong=======");
		
		String choice = "";
		do {
			//Nhap thong tin khach
			System.out.print("Nhap Ho Ten: ");
			String hoTen = sc.nextLine();
			System.out.print("Nhap CMND: ");
			String cmnd = sc.nextLine();
			System.out.print("Nhap dia chi thuong tru: ");
			String diaChi = sc.nextLine();
			System.out.print("Nhap ngay sinh (Nhap nam/thang/ngay): ");
			String ngaySinhStr = sc.nextLine();

			Date ngaySinh = Date.valueOf(ngaySinhStr);
			
			Khach kh = new Khach(creatMaKhach(), CheckDataInput.chuannHoa_str(hoTen), cmnd, 
									CheckDataInput.chuannHoa_str(diaChi), ngaySinh);
			if (Khach_db.insert(kh))
			{
				System.out.println("Them moi khach hang thanh cong!");
			}
			else
			{
				System.out.println("Them moi khach hang that bai. Vui long kiem tra lai!");
			}
			
			//Cho lua chon co nhap tiep khong
			System.out.print("Ban co muon nhap them khach hang nao nua khong (Y/N): ");
			choice = sc.nextLine();
		} while ("Y".equalsIgnoreCase(choice));
	}
	
	//Hien thi khach thue phong:
	public static void showKhach()
	{
		System.out.println("Ma khach" + "\t|\t" + "Ho ten" + "\t\t|\t" 
				+  "CMND" + "\t\t|\t" +  "Dia chi" + "\t\t|\t" +  "Ngay sinh");
		for (Khach kh : Khach_db.select()) {
			System.out.println(kh.getMaKhach() + "\t|\t" + kh.getHoTen() +"\t|\t" 
					+ kh.getCmnd() +"\t|\t" + kh.getDiaChi() +"\t|\t" + kh.getNgaySinh());
		}
	}
	
	//Ham tu dong sinh ma khach (ma khach co do dai = 8):
	public static String creatMaKhach() 
	{		
		String s = "MK000000";
		String maKhach = "";
		String sq_curent = "";
		
		sq_curent = Khach_db.selectMK_max();
		
		if (sq_curent != "")
		{
			sq_curent = sq_curent.trim(); //cat bo khoang trang 2 dau
			String tmp = sq_curent.substring(2); //tao chuoi con khong co chu "MK"
			int sq = Integer.parseInt(tmp) + 1; //convert sq sang kieu int va tang 1
			if (sq >= 999999) 
			{
				System.out.println("Ma khach hiện tại đã lớn hơn MK999999!");
				System.exit(0);
			}
			maKhach = s.substring(0, 8 - Integer.toString(sq).length()) + Integer.toString(sq);	
		}
		else maKhach = "MK000001";
		
		return maKhach;
	}
	//Sua thong tin khach
	public static void suaThongTinKhach() {
		//Nhap thong tin khach
		Khach kh = null;
		boolean existKhachFlag = false;
		String maKhach = "";
		
		do {
			System.out.print("Nhap ma khach can sua: ");
			maKhach = sc.nextLine();
			
			//Kiem tra ton tai khach
			existKhachFlag = Khach_db.kiemTraTonTaiKhach(maKhach);
			if (existKhachFlag == true) {
				System.out.println("Thong tin khach hang da ton tai trong he thong");
				kh = Khach_db.selectOneMK(maKhach);
				System.out.print(kh.toString());
				System.out.println("\n\n                  ===============================================");
				System.out.println("	          =              SUA THONG TIN KHACH            =");
				System.out.println("	          ===============================================\n");
				System.out.println("	          =                                             =");
				System.out.println("	          =              1. Sua ho ten                  =");
				System.out.println("	          =              2. Sua so CMND                 =");
				System.out.println("	          =              3. Sua ngay sinh               =");
				System.out.println("	          =              4. Sua dia chi thuong tru      =");
				System.out.println("	          =              5. Sua tat ca                  =");
				System.out.println("	          =              0. Thoat                       =");
				System.out.println("	          =                                             =");
				System.out.println("	          ===============================================");
				System.out.println("\n\t\t\t    Moi ban nhap lua chon: ");
				
				String luachon;
				luachon = sc.nextLine();
				switch (luachon)
				{		
					case "1":				
						System.out.println("	          1. SUA HO TEN");
						System.out.println("\n\t\t     Nhap ho ten moi: ");
						String hoTen_moi = sc.nextLine();

						kh.setHoTen(CheckDataInput.chuannHoa_str(hoTen_moi));
						if (Khach_db.update(kh))
						{
							System.out.println("Succes!");
						}
						else System.out.println("False!");
						break;
						
					case "2":
						System.out.println("	          2. SUA SO CMND");
						System.out.println("\n\t\t     Nhap so CMND moi: ");
						String cmnd = sc.nextLine();

						kh.setCmnd(cmnd);
						if (Khach_db.update(kh))
						{
							System.out.println("Succes!");
						}
						else System.out.println("False!");
						break;
						
					case "3":
						System.out.println("	          3. SUA NGAY SINH");
						System.out.println("\n\t\t     Nhap ngay sinh moi: ");
						String ngaySinh = sc.nextLine();

						kh.setNgaySinh(Date.valueOf(ngaySinh));
						if (Khach_db.update(kh))
						{
							System.out.println("Succes!");
						}
						else System.out.println("False!");
						break;
						
					case "4":
						System.out.println("	          4. SUA DIA CHI");
						System.out.println("\n\t\t     Nhap dia chi moi: ");
						String diaChi = sc.nextLine();

						kh.setDiaChi(CheckDataInput.chuannHoa_str(diaChi));
						if (Khach_db.update(kh))
						{
							System.out.println("Succes!");
						}
						else System.out.println("False!");
						break;
						
					case "5":
						System.out.println("	          4. SUA TAT CA");
						
						System.out.println("\n\t\t     Nhap ho ten moi: ");
						kh.setHoTen(CheckDataInput.chuannHoa_str(sc.nextLine()));
						
						System.out.println("\n\t\t     Nhap so CMND moi: ");
						kh.setCmnd(sc.nextLine());
						
						System.out.println("\n\t\t     Nhap ngay sinh moi: ");
						kh.setNgaySinh(Date.valueOf(sc.nextLine()));
						
						System.out.println("\n\t\t     Nhap dia chi moi: ");
						kh.setDiaChi(CheckDataInput.chuannHoa_str(sc.nextLine()));
						
						if (Khach_db.update(kh))
						{
							System.out.println("Succes!");
						}
						else System.out.println("False!");
						break;
						
					default:
						System.out.println("\nLua chon khong hop le. Xin kiem tra lai!");
						break;
				}

			} else {
				System.out.println("Thong tin khach hang khong ton tai trong he thong");
				
			}
			
		} while (existKhachFlag == false);
		
		
	}
	
	
}
