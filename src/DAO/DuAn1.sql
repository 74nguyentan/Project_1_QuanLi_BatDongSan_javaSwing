create database DuAn1;

use DuAn1;


create table Userr(
IdUser int identity(1,1) primary key not null,
NameUser nvarchar(50) not null,
UserName varchar(50) not null,
Password varchar(50) not null,
DiaChi nvarchar(100) not null,
Sdt varchar(15) not null,
Email varchar(30),
GioiTinh bit not null,
ChucVu nvarchar(50) not null,
HinhAnh nvarchar(50),
Role int not null,
);

insert into Userr(NameUser,UserName,Password,DiaChi,Sdt,Email,GioiTinh,ChucVu,HinhAnh,Role) values
(N'Nguyễn Văn Tân','NVDoiTac','123',N'Quảng trị','0325772929','Tan@gmail.com','true',N'NV Quản lí đối tác','',4),
(N'Nguyễn Văn Tân','NVSanPham','123',N'Quảng trị','0325772929','Van@gmail.com','true',N'NV Quản lí sản phẩm','',3),
(N'Nguyễn Văn Tân','NVDuAn','123',N'Quảng trị','0325772929','VanTann@gmail.com','true',N'NV Quản lí dự án','',2),
(N'Nguyễn Văn Tân','VanTan','123',N'Quảng trị','0325772929','VanTan@gmail.com','true',N'Giám Đốc','',1);

create table KhachHang(
IdKhachHang int identity(1,1) primary key not null,
TenKhachHang nvarchar(50) not null ,
GioiTinh bit,
DiaChi nvarchar(100) not null,
Sdt varchar(20) not null,
Email nvarchar(50),
AnhKH nvarchar(50),
);

insert into KhachHang(TenKhachHang,GioiTinh,DiaChi,Sdt,Email) values
(N'Nguyễn thị Hằng','false',N'Quảng ninh','0955884667','hanga@gmail.com'),
(N'Nguyễn thị Ngọc','false',N'Quảng trị','0955884667','Ngoc@gmail.com'),
(N'Nguyễn thị F','false',N'Quảng trị','0955884667','kaca@gmail.com'),
(N'Nguyễn Văn K','true',N'Quảng bình','032544777','jda@gmail.com'),
(N'Nguyễn Văn C','false',N'Quảng trị','0622555884','abc@gmail.com'),
(N'Nguyễn Văn A','true',N'Quảng trị','0327772999','Vana@gmail.com'),
(N'Nguyễn Văn B','true',N'Quảng Bình','0566778998','Basd@gmail.com');



create table DoiTac(
IdDoiTac int identity(1,1) primary key not  null,
TenDoiTac nvarchar(30) not null,
LinhVuc nvarchar(30) ,
DiaChi nvarchar(60) not null,
Sdt varchar(15) not null,
Email varchar(15),
--Logo Varbinary(max)  ,
SoVonDaDauTu float,
AnhDT nvarchar(50),
)

insert into DoiTac(TenDoiTac,LinhVuc,DiaChi,Sdt,Email,SoVonDaDauTu) values
(N'Cty AS',N'Xây Dựng',N'Quảng Nam','0325698756','koko@gmail.com',25445),
(N'Cty FC',N'Xây Dựng',N'Quảng Trị','0321112546','ddf@gmail.com',45453),
(N'Cty HANAM',N'Nhà Đất',N'Quảng Ngãi','0456888385','ffc@gmail.com',53536),
(N'Cty NAITHANG',N'Xây Dựng',N'Quảng Ninh','0896888321','sfaac@gmail.com',2323),
(N'Cty XD Phúc Hải',N'Bất Động Sản',N'Quảng bình','0999988321','sdc@gmail.com',36487),
(N'Cty MiaAli',N'Xây Dựng',N'Quảng Trị','0456845631','fc@gmail.com',96533535),
(N'Cty sdfsd',N'Xây Dựng',N'Quảng bình','0456888321','dfc@gmail.com',645465),
(N'Cty BBC',N'Bât động sản',N'Quảng Ngãi','05224448465','ab@gmail.com',455465),
(N'Cty ABC',N'Xây Dựng',N'Quảng Nam','0325111222','abc@gmail.com',455465),
(N'Cty BDS',N'Bất Động Sản',N'Quảng Ngãi','0326777988','dda@gmail.com',78826);

create table DuAn(
IdDuAn int identity(1,1) primary key not null,
TenDuAn nvarchar(50) not null,
LoaiHinh nvarchar(30) not null,
DiaChi nvarchar(50) not null,
DienTich float not null,
ChiPhi float not null,
MucTieu nvarchar(50),
NgayBatDau date,
NgayKetThuc date,
HinhThucQuanLi nvarchar(50) not null,
HinhThucDauTu nvarchar(50) not null,
IdDoiTac int not null,
TrangThai nvarchar(50) ,
HinhAnh nvarchar(50),
constraint fk_DuAn foreign key (IdDoiTac) references DoiTac(IdDoiTac), 
)


insert into DuAn(TenDuAn,LoaiHinh,DiaChi,DienTich,ChiPhi,NgayBatDau,NgayKetThuc,HinhThucQuanLi,HinhThucDauTu,IdDoiTac,TrangThai) values
(N'Chung cư AAA',N'Chung Cư',N'325 Ngô Văn Sở',78,23,'2017-05-29','',N'Chung Cư',N'góp vốn',1,N'Đang xây dựng'),
(N'Chung cư Liên Chiểu',N'Chung Cư',N'108 Bắc Tính',112,550,'2017-05-12','',N'Chung Cư',N'góp vốn',6,N'Đang xây dựng'),
(N'Chung cư KHD',N'Chung Cư',N'08 Hà Huy Tập',7887,5345,'2019-09-02','',N'Chung Cư',N'góp vốn',8,N'Đang xây dựng'),
(N'Chung cư UIO',N'Chung Cư',N'699 Hà Huy Tập',655,868,'2019-08-02','',N'Chung Cư',N'góp vốn',5,N'Đang xây dựng'),
(N'Chung cư Hòa Khánh',N'Chung Cư',N'08 Bạch Đằng',785,988,'2015-05-02','',N'Chung Cư',N'góp vốn',2,N'Đang xây dựng'),
(N'Chung cư Hòa Minh',N'Chung Cư',N'32 Nam Sơn',112,565,'2017-05-02','',N'Chung Cư',N'góp vốn',4,N'Đang xây dựng'),
(N'Đất nền HA Nam',N'Đất nền',N'56 Hà văn Nam',533,785,'2017-05-09','',N'Đất nền',N'góp vốn',1,N'Đang xây dựng'),
(N'Chung cư ABCD',N'Chung Cư',N'08 Hà văn Tính',112,550,'2017-05-02','',N'Chung Cư',N'góp vốn',1,N'Đang xây dựng'),
(N'Chung cư sa',N'Chung Cư',N'03 Hà văn Ngọc',4545,10289,'2019-01-02','',N'Chung Cư',N'góp vốn ',1,N'Đang xây dựng');

create table SanPham(
IdSanPham int identity(1,1) primary key not null,
TenSanPham nvarchar(50) not null,
IdDuAn int not null,
DiaChi nvarchar(50) not null,
DienTich float not null,
GiaTien float ,
MoTa nvarchar(50),
NgayTao date ,
NgayBan date,
ChiTiet nvarchar(50),
trangThai nvarchar(50) not null,
IdKhachHang int  not null,
HinhAnh nvarchar(50),
constraint fk_SanPhammm foreign key (IdKhachHang) references KhachHang(IdKhachHang), 
constraint fk_DuAn_SP foreign key (IdDuAn) references DuAn(IdDuAn),
)

insert into SanPham(TenSanPham,IdDuAn,DiaChi,DienTich,GiaTien,MoTa,NgayTao,NgayBan,ChiTiet,trangThai,IdKhachHang) values
(N'Phòng 22',1,N'03 Hà Văn Tính',40,390,N'đang xây dựng','2019-03-02','',N'phòng đầy đủ tiện nghi',N'chưa bán',3),
(N'Phòng 6',2,N'08 Bạch Đằng',40,390,N'đang xây dựng','2019-03-02','',N'phòng đầy đủ tiện nghi',N'chưa bán',4),
(N'Phòng 7',3,N'03 Hà Văn Ngọc',40,390,N'đang xây dựng','2019-03-02','',N'phòng đầy đủ tiện nghi',N'chưa bán',1),
(N'Phòng 18',4,N'03 Hà Văn Ngọc',40,390,N'đang xây dựng','2019-03-02','',N'phòng đầy đủ tiện nghi',N'chưa bán',6),
(N'Phòng 19',2,N'03 Hà Văn Ngọc',40,390,N'đang xây dựng','2019-03-02','',N'phòng đầy đủ tiện nghi',N'chưa bán',7),
(N'Phòng 20',3,N'03 Hà Văn Ngọc',40,390,N'đang xây dựng','2019-03-02','',N'phòng đầy đủ tiện nghi',N'chưa bán',4),
(N'Phòng 21',2,N'03 Hà Văn Ngọc',40,390,N'đang xây dựng','2019-03-02','',N'phòng đầy đủ tiện nghi',N'chưa bán',6),
(N'Phòng 101',1,N'08 Hà Văn Tính',50,510,N'đang xây dựng','2017-05-02','',N'phòng đầy đủ tiện nghi',N'chưa bán',1),
(N'Phòng 02',2,N'03 Hà Văn Ngọc',40,390,N'đang xây dựng','2019-03-02','',N'phòng đầy đủ tiện nghi',N'chưa bán',7);

create table DanhGia(
IdKhachHang int not null,
NhanXet nvarchar(100),
SoSao int ,
IdSanPham int,
constraint fk_danhgia foreign key (IdKhachHang) references KhachHang(IdKhachHang),
constraint fk_danhgia2 foreign key (IdSanPham) references SanPham(IdSanPham) ,
unique (IdKhachHang,IdSanPham)
);

insert into DanhGia(IdKhachHang,NhanXet,SoSao,IdSanPham) values
(1,N' Giá thành hợp lí',5,2),
(3,N' sản phẩm tốt',5,4),
(4,N' sản phẩm đúng mô tả',5,5),
(5,N' giá tốt phù hợp sản phẩm',5,1),
(6,N' sản phẩm đẹp ngoài mong đợi',5,3);

create table HinhAnh(
IdHinhAnh int identity(1,1) primary key not null,
HinhAnh varchar(50) ,
CheDo nvarchar(20) , 
IdSanPham int not null,
constraint fk_HinhAnh foreign key (IdSanPham) references SanPham(IdSanPham),
)

insert HinhAnh(HinhAnh,CheDo,IdSanPham) values
('1.jpg','Chế độ 360',3),
('E:\Users\IdeaProjects\img\2.jpg','Chế độ 360',2);



/*   sp_ThongKeDA    */
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[sp_ThongKeDA](@Year INT)
AS BEGIN
	SELECT
		TenDuAn DuAn,
		COUNT(DISTINCT sp.IdSanPham) SoSP,
		COUNT(dt.IdDoiTac) SoDT,
		COUNT(kh.IdKhachHang) SoKH,
		SUM(da.ChiPhi) DoanhThu
	
	FROM DuAn da
		JOIN SanPham sp ON da.IdDoiTac=sp.IdDuAn
		JOIN DoiTac dt ON da.IdDoiTac=dt.IdDoiTac
		Join KhachHang kh ON sp.IdKhachHang=kh.IdKhachHang
	WHERE YEAR(NgayTao) = @Year
	GROUP BY TenDuAn
END

/*   sp_ThongKeDoanhThu  */
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[sp_ThongKeDoanhThu]
AS BEGIN
	SELECT
    	YEAR(sp.NgayTao) Nam,
		COUNT(DISTINCT da.IdDuAn) SoDuAn,
		COUNT(sp.IdSanPham) SoSP,
		SUM(sp.GiaTien) DoanhThu,
		MAX(sp.GiaTien) CaoNhat,
		MIN(sp.GiaTien) ThapNhat,
		AVG(sp.GiaTien) TrungBinh
	FROM DuAn da
		JOIN SanPham sp ON da.IdDuAn=sp.IdDuAn
		

	GROUP BY NgayTao
END

/*  sp_ThongKeDoiTac    */
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[sp_ThongKeDoiTac]
AS BEGIN
	SELECT
		TenDoiTac DoiTac,
		COUNT(DISTINCT da.IdDoiTac) SoDuAnDauTu,  /* số dự án đã đầu tư của đối tác */
		SUM(dt.SoVonDaDauTu) SoVonDaDauTu
		
	    FROM DoiTac dt
		JOIN DuAn da ON dt.IdDoiTac=da.IdDoiTac

	GROUP BY TenDoiTac
END
