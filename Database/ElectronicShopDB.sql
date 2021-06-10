CREATE DATABASE ElectronicShop;
--DROP DATABASE ElectronicShop
--Thứ tự chạy DB sau khi Drop: File này -> ListCategory -> ListStatus -> ListManufacturer -> ListProduct -> ListCart -> ListShip 
--							   -> ListCustomerAddress -> ListShipInfo
GO
USE [ElectronicShop]
GO

------------------------------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE Users (
	UserID int PRIMARY KEY identity(1,1),
	Username nvarchar(500),
	Password nvarchar(1000),
	email nvarchar(1000),
	ActiveCode nvarchar(11),
	isSeller int,
	isAdmin int, 
	user_status int
);
GO

INSERT INTO Users VALUES (N'nguyenthegiang', N'nguyenthegiang', N'nguyenthe.giang.775@gmail.com',N'aaaaa', 1, 1, 1);
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

------------------------------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE Category (
	CategoryID int PRIMARY KEY identity(1,1),
	CategoryName nvarchar(1000),
	icon nvarchar(1000)
) ON [PRIMARY]
GO

-------------------------------------------------------------------
--Kì 5: SWP Project
CREATE TABLE ProductStatus (
	StatusID int NOT NULL IDENTITY(1, 1) PRIMARY KEY,
	StatusName nvarchar(1000)
) ON [PRIMARY]
GO

CREATE TABLE UserStatus (
	ID int NOT NULL IDENTITY(1, 1) PRIMARY KEY,
	StatusName nvarchar(1000)
) ON [PRIMARY]
GO

INSERT INTO UserStatus VALUES (N'Active');
INSERT INTO UserStatus VALUES (N'Locked');
INSERT INTO UserStatus VALUES (N'FB Login');



--Manufacturer
CREATE TABLE Manufacturer (
	ManufacturerID int NOT NULL IDENTITY(1, 1) PRIMARY KEY,
	ManufacturerName nvarchar(1000)
) ON [PRIMARY]
GO

CREATE TABLE Product (
	ProductID int NOT NULL IDENTITY(1, 1) PRIMARY KEY,
	ProductName nvarchar(1000),
	Description nvarchar(2000),
	OriginalPrice int, --giá gốc
	SellPrice int,	--giá bán
	SalePercent int,	--phần trăm giảm giá
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

CREATE TABLE Cart (
	UserID int,
	ProductID int,
	Amount int
	constraint userID_in_user FOREIGN KEY(UserID) REFERENCES Users(UserID),
	constraint productID_in_product FOREIGN KEY(ProductID) REFERENCES Product(ProductID),
) ON [PRIMARY]
GO

CREATE TABLE Ship (
	id int NOT NULL IDENTITY(1, 1) PRIMARY KEY,
	CityName nvarchar(1000) ,
	ShipPrice int
) ON [PRIMARY]
GO

--Ship Information
CREATE TABLE CustomerAddress (
	AddressID int NOT NULL IDENTITY(1, 1) PRIMARY KEY,
	AddressDetail nvarchar(1000)
) ON [PRIMARY]
GO

CREATE TABLE ShipInfo (
	UserID int,
	CustomerName nvarchar(1000), --Real name of the Customer
	AddressID int,
	PhoneNum varchar(20),
	constraint addressID_in_CustomerAddress FOREIGN KEY(AddressID) REFERENCES CustomerAddress(AddressID),
	constraint userID_in_user_2 FOREIGN KEY(UserID) REFERENCES Users(UserID),
) ON [PRIMARY]
GO

--FeedBack
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
