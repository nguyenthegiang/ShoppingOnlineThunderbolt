CREATE DATABASE ElectronicShop;
--DROP DATABASE ElectronicShop

GO
USE [ElectronicShop]
GO

-------------------------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE UserStatus (
	ID int NOT NULL IDENTITY(1, 1) PRIMARY KEY,
	StatusName nvarchar(1000)
) ON [PRIMARY]
GO

INSERT INTO UserStatus VALUES (N'Active');
INSERT INTO UserStatus VALUES (N'Locked');
INSERT INTO UserStatus VALUES (N'FB Login');
INSERT INTO UserStatus VALUES (N'Unverified');

-------------------------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE Users (
	UserID int PRIMARY KEY identity(1,1),
	Username nvarchar(500),
	Password nvarchar(1000),
	email nvarchar(1000),
	ActiveCode nvarchar(11),
	isSeller int,
	isAdmin int, 
	StatusID int,
	constraint Status_ID_in_Status FOREIGN KEY(StatusID) REFERENCES UserStatus(ID)
);
GO

INSERT INTO Users VALUES (N'nguyenthegiang', N'nguyenthegiang', N'nguyenthe.giang.775@gmail.com', N'aaaaa', 1, 1, 1);
INSERT INTO Users VALUES (N'buingochuyen', N'buingochuyen', N'a',N'bbbbb', 1, 1,1);
INSERT INTO Users VALUES (N'lehoangchi', N'lehoangchi', N'b', N'bbbbb', 1, 0, 1);
INSERT INTO Users VALUES (N'nguyenthuan', N'nguyenthuan', N'c', N'bbbbb', 1, 0, 1);
INSERT INTO Users VALUES (N'nguyenminhhanh', N'nguyenminhhanh', N'd', N'bbbbb', 1, 0, 1);
INSERT INTO Users VALUES (N'nguyenthithanhmai', N'nguyenthithanhmai', N'e', N'bbbbb', 1, 0, 1);
INSERT INTO Users VALUES (N'nguyentranhoang', N'nguyentranhoang', N'f', N'bbbbb', 0, 0, 1);
INSERT INTO Users VALUES (N'trantatdat', N'trantatdat', N'g', N'bbbbb', 0, 0, 1);
INSERT INTO Users VALUES (N'phungquangthong', N'phungquangthong', N'h', N'bbbbb', 0, 0, 1);
INSERT INTO Users VALUES (N'dinhthethuan', N'dinhthethuan', N'i', N'bbbbb', 0, 0, 1);
INSERT INTO Users VALUES (N'canhoangduc', N'canhoangduc', N'j', N'bbbbb', 0, 0, 1);
INSERT INTO Users VALUES (N'dinhthanhhoang', N'dinhthanhhoang', N'k', N'bbbbb', 0, 0, 1);
GO

-------------------------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE Category (
	CategoryID int PRIMARY KEY identity(1,1),
	CategoryName nvarchar(1000),
	icon nvarchar(1000)
) ON [PRIMARY]
GO

INSERT INTO Category VALUES (N'LAPTOP', N'<i class="fas fa-laptop"></i>');
INSERT INTO Category VALUES (N'PC', N'<i class="fas fa-desktop"></i>');
INSERT INTO Category VALUES (N'THIẾT BỊ VĂN PHÒNG', N'<i class="far fa-folder-open"></i>');
INSERT INTO Category VALUES (N'LINH KIỆN MÁY TÍNH', N'<i class="far fa-keyboard"></i>');
INSERT INTO Category VALUES (N'ĐIỆN THOẠI', N'<i class="fas fa-mobile-alt"></i>');
INSERT INTO Category VALUES (N'TABLET', N'<i class="fas fa-tablet-alt"></i>');
INSERT INTO Category VALUES (N'PHỤ KIỆN', N'<i class="fas fa-headphones"></i>');

-------------------------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE ProductStatus (
	StatusID int NOT NULL IDENTITY(1, 1) PRIMARY KEY,
	StatusName nvarchar(1000)
) ON [PRIMARY]
GO

INSERT INTO ProductStatus VALUES ( N'Available');
INSERT INTO ProductStatus VALUES ( N'StopBusiness');

-------------------------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE Manufacturer (
	ManufacturerID int NOT NULL IDENTITY(1, 1) PRIMARY KEY,
	ManufacturerName nvarchar(1000)
) ON [PRIMARY]
GO

INSERT INTO Manufacturer VALUES ('Dell Inc');
INSERT INTO Manufacturer VALUES ('Hewlett-Packard(HP)');
INSERT INTO Manufacturer VALUES ('ASUSTeK Computer Incorporated (ASUS)');
INSERT INTO Manufacturer VALUES ('Lenovo Group Ltd');
INSERT INTO Manufacturer VALUES ('Micro-Star International(MSI)');--5
INSERT INTO Manufacturer VALUES ('Vivo');
INSERT INTO Manufacturer VALUES ('Sonny');
INSERT INTO Manufacturer VALUES ('Sammsung');
INSERT INTO Manufacturer VALUES ('Apple');
INSERT INTO Manufacturer VALUES ('Kingston');--10
INSERT INTO Manufacturer VALUES ('Acer');
INSERT INTO Manufacturer VALUES ('Canon');
INSERT INTO Manufacturer VALUES ('Intel');
INSERT INTO Manufacturer VALUES ('Gigabyte');
INSERT INTO Manufacturer VALUES ('Logitech');--15
INSERT INTO Manufacturer VALUES ('Fuhlen');
INSERT INTO Manufacturer VALUES ('Xiaomi');
INSERT INTO Manufacturer VALUES ('OPPO');
INSERT INTO Manufacturer VALUES ('Huawei');
INSERT INTO Manufacturer VALUES ('Razer');--20
INSERT INTO Manufacturer VALUES ('SteelSeries');
INSERT INTO Manufacturer VALUES ('Corsair');

-------------------------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE Product (
	ProductID int NOT NULL IDENTITY(1, 1) PRIMARY KEY,
	ProductName nvarchar(1000),
	Description nvarchar(2000),
	OriginalPrice int, 
	SellPrice int,	
	SalePercent int CHECK (SalePercent>=1 AND SalePercent<=100),
	imageLink nvarchar(1000),
	CategoryID int,
	SellerID int,
	Amount int,
	StatusID int,
	ManufacturerID int,
	constraint product_in_category FOREIGN KEY(CategoryID) REFERENCES Category(CategoryID),
	constraint SellerID_in_Users FOREIGN KEY(SellerID) REFERENCES Users(UserID),
	constraint StatusID_in_Status FOREIGN KEY(StatusID) REFERENCES ProductStatus(StatusID),
	constraint ManufacturerID_in_Manufacturer FOREIGN KEY(ManufacturerID) REFERENCES Manufacturer(ManufacturerID)
) ON [PRIMARY]
GO

INSERT INTO Product VALUES (N'Webcam Logitech BRIO Ultra HD Pro', N'Phát video rõ ràng với độ phân giải, tốc độ khung hình, màu sắc và chi tiết siêu việt. Webcam Logitech BRIO mang đến chất lượng Ultra HD 4K ở tốc độ 30 khung hình/giây hoặc 1080p ở tốc độ 30 hoặc 60 khung hình/giây cho độ rõ ràng, mượt mà và chi tiết vượt trội. Nhìn cận cảnh hơn với tính năng thu phóng 5 lần', '6499000','7000000','15', N'Webcam Logitech BRIO Ultra HD Pro.png', 4, 1, 10, 1, 15);
INSERT INTO Product VALUES (N'Laptop Asus Gaming ROG Strix G512-IHN281T (i7 10870H/8GB RAM/512GB SSD/15.6 FHD 144hz/GTX 1650Ti 4GB/Win10/Đen)', N'Laptop Asus ROG Strix G512-IAL013T nổi bật với thiết kế độc đáo và cá tính, thiết kế này được lấy cảm hứng từ BMW Designworks', '27499000',29000000,10, N'Laptop Asus Gaming ROG Strix G512-IHN281T.jpg', 1, 1, 99, 1,3);
INSERT INTO Product VALUES (N'PC GAMING FALCON 054 (R5 3600/B450/16GB RAM/256GB SSD/GTX 1660 SUPER/550W/RGB)', N'Các hệ thống HNC ENTHUSIAST WATERCOOLING đều sử dụng Card màn hình Nvidia Geforce RTX để có hiệu năng chơi game mạnh mẽ nhất.', '20699000',23000000,10, N'PC GAMING FALCON 054.jpg', 2, 1, 30, 1,3);
INSERT INTO Product VALUES (N'Card màn hình MSI GTX 1650 Super GAMING X (4GB GDDR6, 128-bit, HDMI+DP, 1x6-pin)', N'Card màn hình ASUS PH-GT1030-O2G là một trong những card đồ họa Entry-Level tốt nhất cho hệ thống máy tính nhỏ gọn và giải trí tại nhà.', '5499000',5800000,10, N'Card màn hình MSI GTX 1650 Super GAMING X.jpg', 3, 1, 50, 1,5);
INSERT INTO Product VALUES (N'PC GAMING SHARK 024 (R3 4350G/B550/8GB RAM/120GB SSD/RX 570/450W/RGB', N'Các hệ thống HNC ENTHUSIAST WATERCOOLING đều sử dụng Card màn hình Nvidia Geforce RTX để có hiệu năng chơi game mạnh mẽ nhất.', '13699000', 17000000, 10, N'PC GAMING SHARK 024.jpg', 2, 3, 70, 1,5);
INSERT INTO Product VALUES (N'Camera EZVIZ Outdoor CS-C3N-A0-3H2WFRL ( C3N )', N'Phát video rõ ràng với độ phân giải, tốc độ khung hình, màu sắc và chi tiết siêu việt. Webcam Logitech BRIO mang đến chất lượng Ultra HD 4K ở tốc độ 30 khung hình/giây hoặc 1080p ở tốc độ 30 hoặc 60 khung hình/giây cho độ rõ ràng, mượt mà và chi tiết vượt trội. Nhìn cận cảnh hơn với tính năng thu phóng 5 lần', '1190000','1300000','20', N'Camera EZVIZ Outdoor CS-C3N-A0-3H2WFRL.jfif', 4, 3, 20, 1,15);
INSERT INTO Product VALUES (N'Laptop Asus X409JA-EK283T (i3 1005G1/4GB RAM/256GB SSD/14 FHD/Win 10/Bạc)', N'Laptop Asus ROG Strix G512-IAL013T nổi bật với thiết kế độc đáo và cá tính, thiết kế này được lấy cảm hứng từ BMW Designworks', '10499000', 1200000, 10, N'Laptop Asus X409JA-EK283T.jpg', 1, 3, 20, 1,3);
INSERT INTO Product VALUES (N'PC Dựng CAD C006 (i5-10600/B460/16GB RAM/Quadro P620/240GB SSD/450w)11', N'Các hệ thống HNC ENTHUSIAST WATERCOOLING đều sử dụng Card màn hình Nvidia Geforce RTX để có hiệu năng chơi game mạnh mẽ nhất.', '17099000', 2000000, 10,  N'PC Dựng CAD C006.png', 2, 3, 80, 1,3);

--Laptop
INSERT INTO Product VALUES (N'Laptop Dell Vostro 5490 (V4I5106W) (i5 10210U/8G RAM/256GB SSD/14 inch FHD/Win 10/Xám)', N'Laptop LG Gram 14Z90N-V.AR52A5  sở hữu trọng lượng siêu nhẹ mang lại khả năng di chuyển tuyệt vời. Chỉ với 999g, bạn có thể mang máy tính đến bất kỳ nơi nào bạn muốn.', 17189000, 2000000, 10, N'Laptop Dell Vostro 5490 (V4I5106W) (i5 10210U8G RAM256GB SSD14 inch FHDWin 10Xám).jpg', 1, 1, 100, 1,1);
INSERT INTO Product VALUES (N'Laptop MSI Gaming GF63 9RCX 646VN (i5 9300H/8GB RAM/GTX 1050Ti/512GB SSD/15.6 inch FHD/Win 10))', N'Laptop LG Gram 14Z90N-V.AR52A5  sở hữu trọng lượng siêu nhẹ mang lại khả năng di chuyển tuyệt vời. Chỉ với 999g, bạn có thể mang máy tính đến bất kỳ nơi nào bạn muốn.', 18989000, 23000000, 10, N'Laptop MSI Gaming GF63 9RCX 646VN (i5 9300H8GB RAMGTX 1050Ti512GB SSD15.6 inch FHDWin 10)).jpg', 1, 1, 100, 1,5);
INSERT INTO Product VALUES (N'Laptop Workstation Dell Precision 7720 (Xeon E3 1535M V6/64GB RAM/1TB SSD/Quadro P5000/17.3 inch FHD/Win 10 Pro)', N'Laptop LG Gram 14Z90N-V.AR52A5  sở hữu trọng lượng siêu nhẹ mang lại khả năng di chuyển tuyệt vời. Chỉ với 999g, bạn có thể mang máy tính đến bất kỳ nơi nào bạn muốn.', 189999000, 215900000, 10, N'Laptop Workstation Dell Precision 7720 (Xeon E3 1535M V664GB RAM1TB SSDQuadro P500017.3 inch FHDWin 10 Pro)).png', 1, 1, 100, 1,1);
INSERT INTO Product VALUES (N'Laptop Asus Gaming TUF FX505DT-AL118T (R5 3550H/8GB RAM/512GB SSD/15.6 inch FHD 120Hz/GTX 1650 4GB/Win 10/Xám)', N'Laptop LG Gram 14Z90N-V.AR52A5  sở hữu trọng lượng siêu nhẹ mang lại khả năng di chuyển tuyệt vời. Chỉ với 999g, bạn có thể mang máy tính đến bất kỳ nơi nào bạn muốn.', 16999000, 2090000, 10, N'Laptop Asus Gaming TUF FX505DT-AL118T (R5 3550H8GB RAM512GB SSD15.6 inch FHD 120HzGTX 1650 4GBWin 10Xám)).jpg', 1, 1, 100, 1,3);
INSERT INTO Product VALUES (N'Laptop Dell Inspiron 5391 (N3I3001W) (i3 10110U/4GB RAM/128GBSSD/13.3 inch FHD/Win 10/Bạc)', N'Laptop LG Gram 14Z90N-V.AR52A5  sở hữu trọng lượng siêu nhẹ mang lại khả năng di chuyển tuyệt vời. Chỉ với 999g, bạn có thể mang máy tính đến bất kỳ nơi nào bạn muốn.', 14289000, 18900000, 10, N'49826_laptop_dell_inspiron_5391__n3i3001w__01.png', 1, 1, 100, 1,1);
INSERT INTO Product VALUES (N'Laptop Lenovo Thinkpad E14 (20RAS0KX00) (i5 10210U/8GB RAM/256GB SSD/14 FHD/Dos/Đen)', N'Laptop LG Gram 14Z90N-V.AR52A5  sở hữu trọng lượng siêu nhẹ mang lại khả năng di chuyển tuyệt vời. Chỉ với 999g, bạn có thể mang máy tính đến bất kỳ nơi nào bạn muốn.', 16299000, 2000000, 10, N'52609_lenovo_thinkpad_e14__7_.jpg', 1, 1, 100, 1,4);
INSERT INTO Product VALUES (N'Laptop Asus D509DA-EJ286T (R5 3500U/4GB RAM/256GB SSD/15.6 inch FHD/Win 10/Bạc)', N'Laptop LG Gram 14Z90N-V.AR52A5  sở hữu trọng lượng siêu nhẹ mang lại khả năng di chuyển tuyệt vời. Chỉ với 999g, bạn có thể mang máy tính đến bất kỳ nơi nào bạn muốn.', 11299000, 15000000, 10, N'50157_laptop_asus_d509da_ej286t_ryzen_5_bac_01.jpg', 1, 1, 100, 1,3);
INSERT INTO Product VALUES (N'Laptop Asus ROG Zephyrus G15 GA502IU-AL007T (R7 4800HS/8GB RAM/512GB SSD/15.6 inch FHD 144Hz/GTX 1660Ti 6GB/Win10/Balo/Đen)', N'Hệ thống PC Gaming Lion 009 với những thiết bị phần cứng như CPU Intel Pentium G6400, RAM 8GB, VGA RX 570. Đóng vai trò là những bộ máy gaming cơ bản nhất dành cho người mới bắt đầu.', 28999000, 32000000, 10, N'52292_laptop_asus_rog_zephyrus_g15_ga502iu_al007t_r7_den.jpg', 1, 1, 100, 1,3);
INSERT INTO Product VALUES (N'Laptop MSI Modern 14 A10M (692VN) (i5 10210U/8GB RAM/256GB SSD/14 inch FHD/Win 10/Bạc)', N'Laptop LG Gram 14Z90N-V.AR52A5  sở hữu trọng lượng siêu nhẹ mang lại khả năng di chuyển tuyệt vời. Chỉ với 999g, bạn có thể mang máy tính đến bất kỳ nơi nào bạn muốn.', 17799000, 23000000, 15,  N'49979_morden_14_silver__7_.png', 1, 1, 100, 1,5);
INSERT INTO Product VALUES (N'Laptop Lenovo IdeaPad S145-15IWL (81W8001YVN) (i5 1035G1/4GB RAM/256GB SSD/15.6 inch FHD/Win 10/Grey)', N'Laptop LG Gram 14Z90N-V.AR52A5  sở hữu trọng lượng siêu nhẹ mang lại khả năng di chuyển tuyệt vời. Chỉ với 999g, bạn có thể mang máy tính đến bất kỳ nơi nào bạn muốn.', 12299000, 169000000, 15,  N'49369_lenovo_ideapad_s145__7_.jpg', 1, 1, 100, 1,4);
INSERT INTO Product VALUES (N'Laptop Acer Gaming Nitro 5 AN515-43-R9FD (NH.Q6ZSV.003) (Ryzen 5 3550H/8GB/512GB SSD/15.6 inch FHD/GTX1650 4G/Win10/Đen)', N'Laptop LG Gram 14Z90N-V.AR52A5  sở hữu trọng lượng siêu nhẹ mang lại khả năng di chuyển tuyệt vời. Chỉ với 999g, bạn có thể mang máy tính đến bất kỳ nơi nào bạn muốn.', 18289000, 22989000, 10,  N'52376_648.jpg', 1, 1, 100, 1,11);
INSERT INTO Product VALUES (N'Laptop gaming MSI Bravo 15 A4DCR 052VN (R5 4600H/ 8GB/256GB SSD/RX 5300 3GB/15.6FHD/Win 10/Đen)', N'Laptop LG Gram 14Z90N-V.AR52A5  sở hữu trọng lượng siêu nhẹ mang lại khả năng di chuyển tuyệt vời. Chỉ với 999g, bạn có thể mang máy tính đến bất kỳ nơi nào bạn muốn.', 17999000, 15000000, 10,  N'52589_msi_gaming_bravo_15_a4dc_052vn__5_.jpg', 1, 1, 100, 1,5);
INSERT INTO Product VALUES (N'Laptop HP 15s-fq1107TU (193Q3PA) (i3 1005G1/4GB RAM/256GB SSD/15.6 HD/Win10/Bạc)', N'Laptop LG Gram 14Z90N-V.AR52A5  sở hữu trọng lượng siêu nhẹ mang lại khả năng di chuyển tuyệt vời. Chỉ với 999g, bạn có thể mang máy tính đến bất kỳ nơi nào bạn muốn.', 10299000, 14000000, 10, N'53036_hp_15s__5_.jpg', 1, 1, 100, 1,2);

--PC
INSERT INTO Product VALUES (N'PC Trading T003 (i3-10100/B460/8GB RAM/240GB SSD/Quadro P400 x 2/450W)', N'Hệ thống PC Gaming Lion 009 với những thiết bị phần cứng như CPU Intel Pentium G6400, RAM 8GB, VGA RX 570. Đóng vai trò là những bộ máy gaming cơ bản nhất dành cho người mới bắt đầu.', 14099000, 18000000, 10,  N'54512_trading_t003.png', 2, 1, 100, 1,3);
INSERT INTO Product VALUES (N'PC Dựng phim P013 (i5-10400/B460/16GB RAM/RTX 2060/500GB SSD/600w)', N'Hệ thống PC Gaming Lion 009 với những thiết bị phần cứng như CPU Intel Pentium G6400, RAM 8GB, VGA RX 570. Đóng vai trò là những bộ máy gaming cơ bản nhất dành cho người mới bắt đầu.', 19999000, 25000000, 10,  N'56058_pc_d___ng_phim_p013.png', 2, 1, 100, 1,3);
INSERT INTO Product VALUES (N'Mainboard Asus Z11PA-D8 (Dual CPU Server & Workstation)', N'Hệ thống PC Gaming Lion 009 với những thiết bị phần cứng như CPU Intel Pentium G6400, RAM 8GB, VGA RX 570. Đóng vai trò là những bộ máy gaming cơ bản nhất dành cho người mới bắt đầu.', 15999000, 209899000, 10,  N'42754_mainboard_asus_z11pa_d8_dual_cpu_server_amp_workstation_0000_1.jpg', 2, 1, 100, 1,3);
INSERT INTO Product VALUES (N'CPU Intel Xeon Silver 4110 (2.1GHz turbo up to 3.0GHz, 8 nhân, 16 luồng, 11MB Cache, 85W) - Socket Intel LGA 3647', N'Hệ thống PC Gaming Lion 009 với những thiết bị phần cứng như CPU Intel Pentium G6400, RAM 8GB, VGA RX 570. Đóng vai trò là những bộ máy gaming cơ bản nhất dành cho người mới bắt đầu.', 12999000, 170890000, 10,  N'40825_hnc_intel_xeon_silver_right_facing_850x850.jpg', 2, 1, 100, 1,13);
INSERT INTO Product VALUES (N'Workstation Dell Precision 3630 (E2124G/16GB RAM/1TB HDD/Quadro P1000/DVDRW/K+M) (70190803)', N'Hệ thống PC Gaming Lion 009 với những thiết bị phần cứng như CPU Intel Pentium G6400, RAM 8GB, VGA RX 570. Đóng vai trò là những bộ máy gaming cơ bản nhất dành cho người mới bắt đầu.', 28999000, 32000890, 15,  N'48265_dell_precision_3630_021.jpg', 2, 1, 100, 1,1);
INSERT INTO Product VALUES (N'Mainboard Gigabyte C246_WU4', N'Hệ thống PC Gaming Lion 009 với những thiết bị phần cứng như CPU Intel Pentium G6400, RAM 8GB, VGA RX 570. Đóng vai trò là những bộ máy gaming cơ bản nhất dành cho người mới bắt đầu.', 4989000, 5789000, 10,  N'45417_mainboard_gigabyte_c246_wu4_0000_1.jpg', 2, 1, 100, 1,14);
INSERT INTO Product VALUES (N'Server Dell PowerEdge T140 (Xeon E-2224/8GB RAM/1TB HDD/DVDRW) - (42DEFT140-501)', N'Hệ thống PC Gaming Lion 009 với những thiết bị phần cứng như CPU Intel Pentium G6400, RAM 8GB, VGA RX 570. Đóng vai trò là những bộ máy gaming cơ bản nhất dành cho người mới bắt đầu.', 26999000, 37008900, 10, N'52600_dell_t140.jpg', 2, 1, 100, 1,1);
INSERT INTO Product VALUES (N'Mainboard ASUS Z10PA-D8C (DUAL CPU WORKSTATION)', N'Hệ thống PC Gaming Lion 009 với những thiết bị phần cứng như CPU Intel Pentium G6400, RAM 8GB, VGA RX 570. Đóng vai trò là những bộ máy gaming cơ bản nhất dành cho người mới bắt đầu.', 7999000, 8890000,10, N'33404_mainboard_asus_z10pa_d8c_dual_cpu_workstation_0000_1.jpg', 2, 1, 100, 1,3);
INSERT INTO Product VALUES (N'PC Dell OptiPlex 3070 SFF (i5-9500/4GB RAM/1TB HDD/DVDRW/K+M/Ubuntu) (3070SFF-9500-1TB3Y)', N'Hệ thống PC Gaming Lion 009 với những thiết bị phần cứng như CPU Intel Pentium G6400, RAM 8GB, VGA RX 570. Đóng vai trò là những bộ máy gaming cơ bản nhất dành cho người mới bắt đầu.', 12599000, 18900000,10 , N'51904_3070sff.jpg', 2, 1, 100, 1,1);
INSERT INTO Product VALUES (N'PC Acer Aspire XC-885 (i5-8400/4G RAM/1TB HDD/DVDRW/WL/K+M/Dos) (DT.BAQSV.002)', N'Hệ thống PC Gaming Lion 009 với những thiết bị phần cứng như CPU Intel Pentium G6400, RAM 8GB, VGA RX 570. Đóng vai trò là những bộ máy gaming cơ bản nhất dành cho người mới bắt đầu.', 11099000,15000000,10, N'43392_pc_acer_aspire_xc_885_a__1_.jpg', 2, 1, 100, 1,11);
INSERT INTO Product VALUES (N'PC HP ProDesk 400 G5 Desktop Mini (i3-9100T/4GB RAM/256GB SSD/WL/K+M/DOS) (7YD00PA)', N'Hệ thống PC Gaming Lion 009 với những thiết bị phần cứng như CPU Intel Pentium G6400, RAM 8GB, VGA RX 570. Đóng vai trò là những bộ máy gaming cơ bản nhất dành cho người mới bắt đầu.', 9699000, 100000,0, N'48443_hp_prodesk_400_g5_desktop_mini.png', 2, 1, 100, 1,2);
INSERT INTO Product VALUES (N'PC HP 290-p0112d (Pentium G5420/4GB RAM/1TB HDD/DVDRW/WL/K+M/Win 10) (6DV53AA)', N'Hệ thống PC Gaming Lion 009 với những thiết bị phần cứng như CPU Intel Pentium G6400, RAM 8GB, VGA RX 570. Đóng vai trò là những bộ máy gaming cơ bản nhất dành cho người mới bắt đầu.', 7699000, 9890000,10, N'46953_pc_hp_290_021.png', 2, 1, 100, 1,2);
INSERT INTO Product VALUES (N'PC HNC Business Pro P16 (i5 9400/H310/4GB RAM/240GB SSD)', N'Hệ thống PC Gaming Lion 009 với những thiết bị phần cứng như CPU Intel Pentium G6400, RAM 8GB, VGA RX 570. Đóng vai trò là những bộ máy gaming cơ bản nhất dành cho người mới bắt đầu.', 7999000, 880000, 0, N'46612_pcvp_2817.jpg', 2, 1, 100, 1,3);
INSERT INTO Product VALUES (N'PC HP All In One 24-df0041d (i5-10400T/8GB RAM/512GB SSD/23.8 inch FHD/Touch/DVDRW/WL+BT/K+M/Win 10) (180P1AA)', N'Hệ thống PC Gaming Lion 009 với những thiết bị phần cứng như CPU Intel Pentium G6400, RAM 8GB, VGA RX 570. Đóng vai trò là những bộ máy gaming cơ bản nhất dành cho người mới bắt đầu.', 20549000,  25900000, 5, N'57608_hp_aio_24_df0041d_touch_df0040.jpg', 2, 1, 100, 1,2);
INSERT INTO Product VALUES (N'PC mini Asus PN40-BBC061MV (Intel Celeron J4005/Barebone) (90MS0181-M00610)', N'Hệ thống PC Gaming Lion 009 với những thiết bị phần cứng như CPU Intel Pentium G6400, RAM 8GB, VGA RX 570. Đóng vai trò là những bộ máy gaming cơ bản nhất dành cho người mới bắt đầu.', 3499000, 400000, 5,  N'55018_mini_pc_pn40_b_07.png', 2, 1, 100, 1,3);
INSERT INTO Product VALUES (N'PC HP Pavilion TP01-1112d (i5-10400/4GB RAM/1TB HDD/WL+BT/DVDRW/K+M/Win 10) (180S2AA)', N'Hệ thống PC Gaming Lion 009 với những thiết bị phần cứng như CPU Intel Pentium G6400, RAM 8GB, VGA RX 570. Đóng vai trò là những bộ máy gaming cơ bản nhất dành cho người mới bắt đầu.', 11989000, 1300000,0, N'53088_pavilion_590_tp01.jpg', 2, 1, 100, 1,2);
INSERT INTO Product VALUES (N'PC Lenovo IdeaCentre All in One 3 24IIL5 (i5-1035G4/8GB RAM/256GB SSD/23.8 inch FHD/DVDRW/WL+BT/K+M/Win 10) (F0FR005WVN)', N'Hệ thống PC Gaming Lion 009 với những thiết bị phần cứng như CPU Intel Pentium G6400, RAM 8GB, VGA RX 570. Đóng vai trò là những bộ máy gaming cơ bản nhất dành cho người mới bắt đầu.', 16699000, 1900000,5, N'57011_lenovo_monitor_ideacentre_aio_3_24_amd_subseries_gallery.jpg', 2, 1, 100, 1,4);
INSERT INTO Product VALUES (N'PC HP All in One ProOne 400 G6 (i7-10700T/8GB RAM/512GB SSD/23.8 inch/Touch/Radeon 630/DVDRW/WL+BT/K+M/Win 10) (230T5PA)', N'Hệ thống PC Gaming Lion 009 với những thiết bị phần cứng như CPU Intel Pentium G6400, RAM 8GB, VGA RX 570. Đóng vai trò là những bộ máy gaming cơ bản nhất dành cho người mới bắt đầu.', 26949000, 33890000,15, N'56081_hp_all_in_one_proone_400_g6.jpg', 2, 1, 100, 1,2);
INSERT INTO Product VALUES (N'PC Lenovo V50s (i3-10100/4GB RAM/256GB SSD/DVDRW/WL+BT/K+M/No OS) (11EF003HVA)', N'Hệ thống PC Gaming Lion 009 với những thiết bị phần cứng như CPU Intel Pentium G6400, RAM 8GB, VGA RX 570. Đóng vai trò là những bộ máy gaming cơ bản nhất dành cho người mới bắt đầu.', 8889000, 11890000,20, N'55974_lenovo_v50s_001.png', 2, 1, 100, 1,4);

--Thiết bị văn phòng
INSERT INTO Product VALUES (N'HP LaserJet Pro M402n (C5F93A)', N'Vỏ ngoài màu trắng sang trọng, thiết kế với kiểu dáng hiện đại, nút bấm được bố trí hợp lý mạng lại vẻ đẹp và sự đơn giản dễ sử dụng', 3999000, 4310000,10, N'36116_chinh_dien_may_in_hp_m402n_in_laser.jpg', 3, 1, 100, 1,2);
INSERT INTO Product VALUES (N'Máy in Canon SELPHY CP1300 (Máy in di động - In màu - Màu đen)', N'Vỏ ngoài màu trắng sang trọng, thiết kế với kiểu dáng hiện đại, nút bấm được bố trí hợp lý mạng lại vẻ đẹp và sự đơn giản dễ sử dụng', 3299000, 37890000,10, N'57176_canon_selphy_cp1300_black__001.jpg', 3, 1, 100, 1,12);
INSERT INTO Product VALUES (N'Lọ mực XPpro dùng cho máy in laser HP, CANON (140 gam)', N'Vỏ ngoài màu trắng sang trọng, thiết kế với kiểu dáng hiện đại, nút bấm được bố trí hợp lý mạng lại vẻ đẹp và sự đơn giản dễ sử dụng', 139000, 170000,5, N'51037_l____m___c_xppro.jpg', 3, 1, 100, 1,2);
INSERT INTO Product VALUES (N'Trục Cao Su 12A Máy In HP (1010/1015/1020/3030/3050/305)', N'Vỏ ngoài màu trắng sang trọng, thiết kế với kiểu dáng hiện đại, nút bấm được bố trí hợp lý mạng lại vẻ đẹp và sự đơn giản dễ sử dụng', 149000, 2089000,10, N'51053_truc_cao_su_12a.jpg', 3, 1, 100, 1,2);
INSERT INTO Product VALUES (N'Máy chiếu đa năng ViewSonic PA502SP', N'Vỏ ngoài màu trắng sang trọng, thiết kế với kiểu dáng hiện đại, nút bấm được bố trí hợp lý mạng lại vẻ đẹp và sự đơn giản dễ sử dụng', 7999000, 10000000,10, N'44931_may_chieu_da_nang_viewsonic_pa502sp__1_.jpg', 3, 1, 100, 1,7);
INSERT INTO Product VALUES (N'Máy Chiếu ACER - X118HP', N'Vỏ ngoài màu trắng sang trọng, thiết kế với kiểu dáng hiện đại, nút bấm được bố trí hợp lý mạng lại vẻ đẹp và sự đơn giản dễ sử dụng', 8789000, 1000000,5, N'54681_may_chieu_acer_x118hp__1_.jpg', 3, 1, 100, 1,11);
INSERT INTO Product VALUES (N'Máy Hủy Tài Liệu Silicon PS 812C', N'Vỏ ngoài màu trắng sang trọng, thiết kế với kiểu dáng hiện đại, nút bấm được bố trí hợp lý mạng lại vẻ đẹp và sự đơn giản dễ sử dụng', 1909000, 2765000, 15, N'37645_812.jpg', 3, 1, 100, 1,7);
INSERT INTO Product VALUES (N'Máy chấm công vân tay Ronal Jack F18', N'Vỏ ngoài màu trắng sang trọng, thiết kế với kiểu dáng hiện đại, nút bấm được bố trí hợp lý mạng lại vẻ đẹp và sự đơn giản dễ sử dụng', 4299000, 6000000,15, N'48014_may_cham_cong_van_tay_ronal_jack_f18.jpg', 3, 1, 100, 1,7);
INSERT INTO Product VALUES (N'Máy chấm công vân tay Ronal Jack X928C', N'Vỏ ngoài màu trắng sang trọng, thiết kế với kiểu dáng hiện đại, nút bấm được bố trí hợp lý mạng lại vẻ đẹp và sự đơn giản dễ sử dụng', 3699000, 450000,5, N'48015_may_cham_cong_van_tay_va_the_ronal_jack_x928c.jpg', 3, 1, 100, 1,7);
INSERT INTO Product VALUES (N'Máy Fax Brother 2840', N'Vỏ ngoài màu trắng sang trọng, thiết kế với kiểu dáng hiện đại, nút bấm được bố trí hợp lý mạng lại vẻ đẹp và sự đơn giản dễ sử dụng', 4499000, 600000,10, N'44245_chinh_dien_may_fax_brother_2840.jpg', 3, 1, 100, 1,7);
INSERT INTO Product VALUES (N'Bảng Vẽ Wacom Intuos M, Bluetooth, Pistachio (CTL-6100WL)', N'Vỏ ngoài màu trắng sang trọng, thiết kế với kiểu dáng hiện đại, nút bấm được bố trí hợp lý mạng lại vẻ đẹp và sự đơn giản dễ sử dụng', 5059000, 750000,10, N'46158_mat_truoc_bang_ve_wacom_intuos_m_bluetooth_pistachio.jpg', 3, 1, 100, 1,7);

--Linh kiện máy tính
INSERT INTO Product VALUES (N'CPU Intel Xeon Silver 4110 (2.1GHz turbo up to 3.0GHz, 8 nhân, 16 luồng, 11MB Cache, 85W) - Socket Intel LGA 3647', N'Vỏ ngoài màu trắng sang trọng, thiết kế với kiểu dáng hiện đại, nút bấm được bố trí hợp lý mạng lại vẻ đẹp và sự đơn giản dễ sử dụng', 10999000, 1500000, 5, N'40825_hnc_intel_xeon_silver_right_facing_850x850.jpg', 4, 3, 100, 1,13);
INSERT INTO Product VALUES (N'Nguồn FSP Power Supply HYDRO Series Model HD600 Active PFC (80 Plus Bronze/Màu Đen)', N'Vỏ ngoài màu trắng sang trọng, thiết kế với kiểu dáng hiện đại, nút bấm được bố trí hợp lý mạng lại vẻ đẹp và sự đơn giản dễ sử dụng', 1499000, 220000,10, N'31135_nguon_fsp_hydro_series_600w_model_hd600_0001_2.jpg', 4, 3, 100, 1,3);
INSERT INTO Product VALUES (N'Chuột Fuhlen A37GS Optical Wireless', N'Vỏ ngoài màu trắng sang trọng, thiết kế với kiểu dáng hiện đại, nút bấm được bố trí hợp lý mạng lại vẻ đẹp và sự đơn giản dễ sử dụng', 279000,300000,0, N'33713_mouse_fuhlen_a37gs_optical_wireless.jpg', 4, 3, 100, 1,16);
INSERT INTO Product VALUES (N'DDRam 4 Kingston ECC 32GB/2666 - KSM26RD4/32HAI Registered', N'Vỏ ngoài màu trắng sang trọng, thiết kế với kiểu dáng hiện đại, nút bấm được bố trí hợp lý mạng lại vẻ đẹp và sự đơn giản dễ sử dụng', 6190000, 10000000,10, N'47682_kingston_ecc__32gb2666_ksm26rd432hai.jpg', 4, 3, 100, 1,10);

--Điện thoại
INSERT INTO Product VALUES (N'Điện thoại iPhone 12 Pro 256GB', N'Thiết kế bên ngoài chứa đựng một hiệu năng cực khủng bên trong, kèm theo đó là một loạt các công nghệ ấn tượng về camera, kết nối 5G lần đầu được xuất hiện.', 34990000, 55690000,10, N'iphone-12-pro-max-vang-new-600x600-200x200.jpg', 5, 6, 100, 1,9);
INSERT INTO Product VALUES (N'Điện thoại Samsung Galaxy S21 5G', N'Thiết kế bên ngoài chứa đựng một hiệu năng cực khủng bên trong, kèm theo đó là một loạt các công nghệ ấn tượng về camera, kết nối 5G lần đầu được xuất hiện.', 20990000, 40000000,10, N'samsung-galaxy-s21-tim-600x600-200x200.jpg', 5, 6, 100, 1,8);
INSERT INTO Product VALUES (N'Điện thoại Vivo Y12s (3GB/32GB)', N'Thiết kế bên ngoài chứa đựng một hiệu năng cực khủng bên trong, kèm theo đó là một loạt các công nghệ ấn tượng về camera, kết nối 5G lần đầu được xuất hiện.', 2990000, 4000000,10, N'vivo-y12s-den-200x200.jpg', 5, 6, 100, 1,6);
INSERT INTO Product VALUES (N'Điện thoại Xiaomi Redmi 9T (6GB/128GB)', N'Thiết kế bên ngoài chứa đựng một hiệu năng cực khủng bên trong, kèm theo đó là một loạt các công nghệ ấn tượng về camera, kết nối 5G lần đầu được xuất hiện.', 4990000, 6690000,10, N'xiaomi-redmi-9t-6gb-110621-080650-400x400.jpg', 5, 6, 100, 1,17);
INSERT INTO Product VALUES (N'Điện thoại Samsung Galaxy M51', N'Thiết kế bên ngoài chứa đựng một hiệu năng cực khủng bên trong, kèm theo đó là một loạt các công nghệ ấn tượng về camera, kết nối 5G lần đầu được xuất hiện.', 9490000, 1390000,10,N'637217840297210976_samsung-galaxy-m51-1.png', 5, 6, 100, 1,8);
INSERT INTO Product VALUES (N'Điện thoại OPPO Reno5', N'Thiết kế bên ngoài chứa đựng một hiệu năng cực khủng bên trong, kèm theo đó là một loạt các công nghệ ấn tượng về camera, kết nối 5G lần đầu được xuất hiện.', 8690000, 12900000,0,N'7cf04cfb464a9385452ed6ef24e8e450.jpg', 5, 6, 100, 1,18);

--Tablet
INSERT INTO Product VALUES (N'Máy tính bảng Huawei MatePad T10s (Nền tảng Huawei Mobile Service)', N'Với vi xử lý 8 nhân mở ra một thế giới giải trí mượt mà, sống động từng khoảnh khắc với màn hình cực lớn, hé lộ một chiếc máy tính bảng tốt trong tầm giá mà bất kỳ ai cũng đều yêu thích.', 5190000, 9800000,0,N'tab-m10-fhd-plus-600-600x600.jpg', 6, 5, 100, 1,19);
INSERT INTO Product VALUES (N'Máy tính bảng Samsung Galaxy Tab S6 Lite', N'Với vi xử lý 8 nhân mở ra một thế giới giải trí mượt mà, sống động từng khoảnh khắc với màn hình cực lớn, hé lộ một chiếc máy tính bảng tốt trong tầm giá mà bất kỳ ai cũng đều yêu thích.', 8290000, 13590000,10, N'samsung-galaxy-tab-s6-lite-600x600-2-200x200.jpg', 6, 5, 300, 1,8);
INSERT INTO Product VALUES (N'Máy tính bảng Lenovo Tab M10', N'Với vi xử lý 8 nhân mở ra một thế giới giải trí mượt mà, sống động từng khoảnh khắc với màn hình cực lớn, hé lộ một chiếc máy tính bảng tốt trong tầm giá mà bất kỳ ai cũng đều yêu thích.', 4090000, 7090000,10, N'unnamed.jpg', 6, 5, 250, 1,4);
INSERT INTO Product VALUES (N'Máy tính bảng iPad Pro 11 inch Wifi 128GB (2020)', N'Với vi xử lý 8 nhân mở ra một thế giới giải trí mượt mà, sống động từng khoảnh khắc với màn hình cực lớn, hé lộ một chiếc máy tính bảng tốt trong tầm giá mà bất kỳ ai cũng đều yêu thích.', 21490000,  51290000,10,N'ipad-pro-12-select-wifi-silver-202003-fmt-whh.png', 6, 5, 200, 1,9);

--Phụ kiện
INSERT INTO Product VALUES (N'Tai nghe Kingston HyperX Cloud Stinger Gaming Black (HX-HSCS-BK/AS)', N'Chiếc tai nghe giá rẻ và bán chạy nhất của Kingston HyperX', 1299000, 1890000,0, N'34182_tai_nghe_kingston_hyperx_cloud_stinger_gaming_black_hx_hscs_bkas_0001_1.jpg', 7, 4, 100, 1,10)
INSERT INTO Product VALUES (N'Tai nghe Razer Kraken Tournament Edition Wired Gaming Headset Green RZ04-02051100-R3M1', N'Chiếc tai nghe giá rẻ và bán chạy nhất của Kingston HyperX', 2399000, 3000000,5, N'44378_tong_the_tai_nghe_razer_kraken_tournament_edition_wired_gaming_headset_green.jpg', 7, 4, 100, 1,20);
INSERT INTO Product VALUES (N'Tai nghe Bluetooth Asus ROG Strix Fusion 700 Gaming', N'Chiếc tai nghe giá rẻ và bán chạy nhất của Kingston HyperX', 3999000, 7599000,5, N'51501_rog_strix_fusion_700_photo_6.png', 7, 4, 100, 1,3);
INSERT INTO Product VALUES (N'Tai nghe SteelSeries Arctis 7 Edition White 61508', N'Chiếc tai nghe giá rẻ và bán chạy nhất của Kingston HyperX', 3999000, 7599000,5, N'50383_tai_nghe_steelseries_arctis_7_2019_edition_white_61508_0002_1.jpg', 7, 4, 100, 1,21);
INSERT INTO Product VALUES (N'Tai nghe Corsair HS35 Stereo Blue', N'Chiếc tai nghe giá rẻ và bán chạy nhất của Kingston HyperX', 929000, 1559000,10,N'47734_tai_nghe_corsair_hs35_stereo_blue_0005_1.jpg', 7, 4, 100, 1,22);
INSERT INTO Product VALUES (N'Tai nghe gaming Logitech G331', N'Chiếc tai nghe giá rẻ và bán chạy nhất của Kingston HyperX', 1199000, 3399000,10, N'46343_tai_nghe_logitech_g331_stereo_gaming_headset_0001_1.jpg', 7, 4, 100, 1,15);
INSERT INTO Product VALUES (N'Bàn di chuột Asus ROG Sheath Electro Punk (L900 x W440 x H3 mm)', N'ROG Sheath Electro Punk có bề mặt được dệt tinh xảo, hoàn hảo để đảm bảo chuột chơi game của bạn lướt nhẹ nhàng. Dù cài đặt trò chơi ưa thích của bạn là gì - bạn được đảm bảo kiểm soát chính xác từng pixel.', 1069000, 2069000,10, N'57301_ban_di_chuot_asus_rog_sheath_electro_punk_l900_x_w440_x_h3_mm_0003_4.jpg', 7, 4, 100, 1,3);
INSERT INTO Product VALUES (N'Bàn di chuột Razer Firefly Cloth Edition (RZ02-02000100-R3M1)', N'ROG Sheath Electro Punk có bề mặt được dệt tinh xảo, hoàn hảo để đảm bảo chuột chơi game của bạn lướt nhẹ nhàng. Dù cài đặt trò chơi ưa thích của bạn là gì - bạn được đảm bảo kiểm soát chính xác từng pixel.', 1599000, 3399000,10,N'34293_mouse_pad_razer_goliathus_speed_cosmic_edition_soft_large_rz02_01910300_r3m2_0000_1.jpg', 7, 4, 100, 1,20);
INSERT INTO Product VALUES (N'Túi đeo Shoulder Bag Corsair Vengeance H', N'Chiếc tai nghe giá rẻ và bán chạy nhất của Kingston HyperX', 399000, 709000,10, N'35685_ben_trong_tui_deo_shoulder_bag_corsair_vengeance_h.jpg', 7, 4, 100, 1,22);

-------------------------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE [dbo].[Information](
	[description] [nvarchar](max) NULL,
	[address] [nvarchar](max) NULL,
	[email] [nvarchar](max) NULL,
	[phone] [nvarchar](max) NULL,
	[fax] [nvarchar](max) NULL
) ON [PRIMARY]
GO

INSERT [dbo].[Information] ([description], [address], [email], [phone], [fax]) 
VALUES (N'© 2020 Công Ty Cổ Phần Máy Tính Computer ERA', N'Số 129 + 131, phố Lê Thanh Nghị, Phường Đồng Tâm, Quận Hai Bà Trưng, Hà Nội', N'hnc@computerera.com', N'1900 1903', N'1900 1904')

-------------------------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE Cart (
	UserID int,
	ProductID int,
	Amount int
	constraint userID_in_user FOREIGN KEY(UserID) REFERENCES Users(UserID),
	constraint productID_in_product FOREIGN KEY(ProductID) REFERENCES Product(ProductID),
) ON [PRIMARY]
GO
select * from Product
INSERT INTO Cart VALUES (8, 1, 1);
INSERT INTO Cart VALUES (8, 2, 1);
INSERT INTO Cart VALUES (8, 3, 1);
INSERT INTO Cart VALUES (8, 4, 1);
INSERT INTO Cart VALUES (8, 5, 1);
INSERT INTO Cart VALUES (8, 6, 1);
INSERT INTO Cart VALUES (8, 7, 1);
INSERT INTO Cart VALUES (8, 8, 1);
INSERT INTO Cart VALUES (8, 9, 1);
INSERT INTO Cart VALUES (7, 12, 1);
INSERT INTO Cart VALUES (7, 13, 1);
INSERT INTO Cart VALUES (7, 16, 1);
INSERT INTO Cart VALUES (7, 19, 1);
INSERT INTO Cart VALUES (7, 20, 1);
INSERT INTO Cart VALUES (7, 25, 1);
INSERT INTO Cart VALUES (7, 26, 1);
INSERT INTO Cart VALUES (7, 29, 1);
INSERT INTO Cart VALUES (7, 30, 1);
INSERT INTO Cart VALUES (9, 31, 2);
INSERT INTO Cart VALUES (9, 32, 3);
INSERT INTO Cart VALUES (9, 33, 5);
INSERT INTO Cart VALUES (9, 34, 1);
INSERT INTO Cart VALUES (9, 35, 7);
INSERT INTO Cart VALUES (9, 38, 8);

-------------------------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE Ship (
	id int NOT NULL IDENTITY(1, 1) PRIMARY KEY,
	CityName nvarchar(1000) ,
	ShipPrice int
) ON [PRIMARY]
GO

INSERT INTO Ship VALUES (N'Hà Nội', 20000);
INSERT INTO Ship VALUES (N'TP Hồ Chí Minh', 100000);
INSERT INTO Ship VALUES (N'Đà Nẵng', 70000);
INSERT INTO Ship VALUES (N'Cần Thơ', 80000);
INSERT INTO Ship VALUES (N'Hải Phòng', 40000);
INSERT INTO Ship VALUES (N'Việt Trì', 10000);
INSERT INTO Ship VALUES (N'Biên Hòa', 120000);
INSERT INTO Ship VALUES (N'Nha Trang', 90000);

-------------------------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE CustomerAddress (
	AddressID int NOT NULL IDENTITY(1, 1) PRIMARY KEY,
	AddressDetail nvarchar(1000)
) ON [PRIMARY]
GO

INSERT INTO CustomerAddress VALUES (N'160 Hoang Quoc Viet, Ha Noi');
INSERT INTO CustomerAddress VALUES (N'25 Ham Nghi, Quan Hai Ba Trung, Ha Noi');
INSERT INTO CustomerAddress VALUES (N'16 Pho Hoang Hoa Than, Long Bien, Ha Noi');
INSERT INTO CustomerAddress VALUES (N'221B Baker Street, London');
INSERT INTO CustomerAddress VALUES (N'1 Dai lo Thang Long, Ha Noi');
INSERT INTO CustomerAddress VALUES (N'Vinhomes Smart City, Ha Noi');

-------------------------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE ShipInfo (
	UserID int,
	CustomerName nvarchar(1000), --Real name of the Customer
	AddressID int,
	PhoneNum varchar(20),
	constraint addressID_in_CustomerAddress FOREIGN KEY(AddressID) REFERENCES CustomerAddress(AddressID),
	constraint userID_in_user_2 FOREIGN KEY(UserID) REFERENCES Users(UserID),
) ON [PRIMARY]
GO

INSERT INTO ShipInfo VALUES (7, N'Nguyen Tran Hoang', 1, '0956236487');
INSERT INTO ShipInfo VALUES (8, N'Tran Tat Dat', 2, '0958134965');
INSERT INTO ShipInfo VALUES (9, N'Phung Quang Thong', 3, '0983516472');
INSERT INTO ShipInfo VALUES (10, N'Dinh The Thuan', 4, '0935164825');
INSERT INTO ShipInfo VALUES (11, N'Can Hoang Duc', 5, '0936323531');
INSERT INTO ShipInfo VALUES (12, N'Dinh Thanh Hoang', 6, '0933441221');

-------------------------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE Feedback (
	UserID int,
	ProductID int,
	Star int, --1-5
	FeedbackDetail nvarchar(2000),
	constraint userID_in_user_3 FOREIGN KEY(UserID) REFERENCES Users(UserID),
	constraint productID_in_product_2 FOREIGN KEY(ProductID) REFERENCES Product(ProductID),
	constraint valid_star CHECK (Star < 6 AND Star > 0)
) ON [PRIMARY]
GO

INSERT INTO Feedback VALUES (7, 1, 5, N'Very good');
INSERT INTO Feedback VALUES (8, 3, 4, N'Good');
INSERT INTO Feedback VALUES (9, 30, 3, N'Not Bad');
INSERT INTO Feedback VALUES (10, 24, 5, N'Sản phẩm rất tốt');
INSERT INTO Feedback VALUES (11, 1, 1, N'Gửi sai sản phẩm');
INSERT INTO Feedback VALUES (12, 50, 5, N'Very good');
INSERT INTO Feedback VALUES (7, 60, 5, N'It good');
INSERT INTO Feedback VALUES (12, 70, 5, N'Very good');
INSERT INTO Feedback VALUES (11, 69, 5, N'Very good');
INSERT INTO Feedback VALUES (9, 22, 5, N'Good');
INSERT INTO Feedback VALUES (7, 56, 5, N'Rất tốt');
INSERT INTO Feedback VALUES (8, 70, 3, N'Đóng gói chưa tốt');
INSERT INTO Feedback VALUES (7, 68, 2, N'Sai màu sản phẩm');
INSERT INTO Feedback VALUES (8, 71, 2, N'Gửi thiếu linh kiện');
