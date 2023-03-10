USE [master]
GO
/****** Object:  Database [HotelBookingL002]    Script Date: 12/19/2020 2:08:48 PM ******/
CREATE DATABASE [HotelBookingL002]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'HotelBookingL002', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.MSSQLSERVER\MSSQL\DATA\HotelBookingL002.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'HotelBookingL002_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.MSSQLSERVER\MSSQL\DATA\HotelBookingL002_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [HotelBookingL002] SET COMPATIBILITY_LEVEL = 140
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [HotelBookingL002].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [HotelBookingL002] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [HotelBookingL002] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [HotelBookingL002] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [HotelBookingL002] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [HotelBookingL002] SET ARITHABORT OFF 
GO
ALTER DATABASE [HotelBookingL002] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [HotelBookingL002] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [HotelBookingL002] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [HotelBookingL002] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [HotelBookingL002] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [HotelBookingL002] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [HotelBookingL002] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [HotelBookingL002] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [HotelBookingL002] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [HotelBookingL002] SET  DISABLE_BROKER 
GO
ALTER DATABASE [HotelBookingL002] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [HotelBookingL002] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [HotelBookingL002] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [HotelBookingL002] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [HotelBookingL002] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [HotelBookingL002] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [HotelBookingL002] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [HotelBookingL002] SET RECOVERY FULL 
GO
ALTER DATABASE [HotelBookingL002] SET  MULTI_USER 
GO
ALTER DATABASE [HotelBookingL002] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [HotelBookingL002] SET DB_CHAINING OFF 
GO
ALTER DATABASE [HotelBookingL002] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [HotelBookingL002] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [HotelBookingL002] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'HotelBookingL002', N'ON'
GO
ALTER DATABASE [HotelBookingL002] SET QUERY_STORE = OFF
GO
USE [HotelBookingL002]
GO
/****** Object:  Table [dbo].[tblBooking]    Script Date: 12/19/2020 2:08:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblBooking](
	[bookingID] [int] IDENTITY(1,1) NOT NULL,
	[email] [nvarchar](50) NULL,
	[totalPrice] [float] NULL,
	[bookingDate] [datetime] NULL,
	[status] [bit] NULL,
	[verified] [bit] NULL,
 CONSTRAINT [PK_tblBooking] PRIMARY KEY CLUSTERED 
(
	[bookingID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblBookingDetail]    Script Date: 12/19/2020 2:08:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblBookingDetail](
	[bookingDetailID] [int] IDENTITY(1,1) NOT NULL,
	[bookingID] [int] NULL,
	[roomID] [int] NULL,
	[price] [float] NULL,
	[checkInDate] [datetime] NULL,
	[checkOutDate] [datetime] NULL,
	[feedback] [int] NULL,
 CONSTRAINT [PK_Table_1] PRIMARY KEY CLUSTERED 
(
	[bookingDetailID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblDiscountCode]    Script Date: 12/19/2020 2:08:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblDiscountCode](
	[discountID] [nvarchar](20) NOT NULL,
	[expiredDate] [datetime] NULL,
	[percent] [int] NULL,
 CONSTRAINT [PK_tblDiscountCode] PRIMARY KEY CLUSTERED 
(
	[discountID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblRole]    Script Date: 12/19/2020 2:08:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblRole](
	[roleID] [int] NOT NULL,
	[roleName] [nvarchar](10) NULL,
 CONSTRAINT [PK_tblRole] PRIMARY KEY CLUSTERED 
(
	[roleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblRoom]    Script Date: 12/19/2020 2:08:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblRoom](
	[roomID] [int] NOT NULL,
	[roomTypeID] [int] NULL,
	[status] [bit] NULL,
 CONSTRAINT [PK_tblRooms] PRIMARY KEY CLUSTERED 
(
	[roomID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblRoomType]    Script Date: 12/19/2020 2:08:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblRoomType](
	[roomTypeID] [int] NOT NULL,
	[roomType] [nvarchar](20) NOT NULL,
	[price] [float] NULL,
	[description] [nvarchar](100) NULL,
 CONSTRAINT [PK_tblRoomTypes] PRIMARY KEY CLUSTERED 
(
	[roomTypeID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblUser]    Script Date: 12/19/2020 2:08:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblUser](
	[email] [nvarchar](50) NOT NULL,
	[password] [nvarchar](50) NULL,
	[roleID] [int] NULL,
	[phone] [nvarchar](20) NULL,
	[name] [nvarchar](50) NULL,
	[address] [nvarchar](150) NULL,
	[createdDate] [datetime] NULL,
	[status] [bit] NULL,
 CONSTRAINT [PK_tblUsers] PRIMARY KEY CLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblUserDiscount]    Script Date: 12/19/2020 2:08:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblUserDiscount](
	[userDiscountID] [int] IDENTITY(1,1) NOT NULL,
	[email] [nvarchar](50) NULL,
	[discountID] [nvarchar](10) NULL,
	[isUsed] [bit] NULL,
 CONSTRAINT [PK_tblUserDiscount] PRIMARY KEY CLUSTERED 
(
	[userDiscountID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[tblBooking] ON 

INSERT [dbo].[tblBooking] ([bookingID], [email], [totalPrice], [bookingDate], [status], [verified]) VALUES (1, N'cus1@gmail.com', NULL, CAST(N'2020-12-20T00:00:00.000' AS DateTime), 1, 0)
INSERT [dbo].[tblBooking] ([bookingID], [email], [totalPrice], [bookingDate], [status], [verified]) VALUES (2, N'cus2@gmail.com', 200000, CAST(N'2020-12-28T00:00:00.000' AS DateTime), 1, 0)
INSERT [dbo].[tblBooking] ([bookingID], [email], [totalPrice], [bookingDate], [status], [verified]) VALUES (3, N'cus2@gmail.com', NULL, CAST(N'2020-12-20T00:00:00.000' AS DateTime), 1, 0)
INSERT [dbo].[tblBooking] ([bookingID], [email], [totalPrice], [bookingDate], [status], [verified]) VALUES (4, N'cus1@gmail.com', 1000000, CAST(N'1900-01-10T00:00:00.000' AS DateTime), 1, 0)
INSERT [dbo].[tblBooking] ([bookingID], [email], [totalPrice], [bookingDate], [status], [verified]) VALUES (5, N'cus2@gmail.com', 883500, CAST(N'2020-12-18T00:00:00.000' AS DateTime), 1, 0)
INSERT [dbo].[tblBooking] ([bookingID], [email], [totalPrice], [bookingDate], [status], [verified]) VALUES (6, N'cus2@gmail.com', 4000000, CAST(N'2020-12-18T00:00:00.000' AS DateTime), 1, 0)
INSERT [dbo].[tblBooking] ([bookingID], [email], [totalPrice], [bookingDate], [status], [verified]) VALUES (7, N'baopqse62518@fpt.edu.vn', 4950000, CAST(N'2020-12-19T00:00:00.000' AS DateTime), 1, 0)
INSERT [dbo].[tblBooking] ([bookingID], [email], [totalPrice], [bookingDate], [status], [verified]) VALUES (8, N'baopqse62518@fpt.edu.vn', 3800000, CAST(N'2020-12-19T00:00:00.000' AS DateTime), 1, 0)
INSERT [dbo].[tblBooking] ([bookingID], [email], [totalPrice], [bookingDate], [status], [verified]) VALUES (9, N'baopqse62518@fpt.edu.vn', 744000, CAST(N'2020-12-19T00:00:00.000' AS DateTime), 1, 0)
INSERT [dbo].[tblBooking] ([bookingID], [email], [totalPrice], [bookingDate], [status], [verified]) VALUES (10, N'baopqse62518@fpt.edu.vn', 5000000, CAST(N'2020-12-19T00:00:00.000' AS DateTime), 1, 0)
INSERT [dbo].[tblBooking] ([bookingID], [email], [totalPrice], [bookingDate], [status], [verified]) VALUES (11, N'cus2@gmail.com', 800000, CAST(N'2020-12-19T00:00:00.000' AS DateTime), 1, 0)
INSERT [dbo].[tblBooking] ([bookingID], [email], [totalPrice], [bookingDate], [status], [verified]) VALUES (12, N'baopqse62518@fpt.edu.vn', 2500000, CAST(N'2020-12-19T00:00:00.000' AS DateTime), 1, 0)
INSERT [dbo].[tblBooking] ([bookingID], [email], [totalPrice], [bookingDate], [status], [verified]) VALUES (13, N'baopqse62518@fpt.edu.vn', 264000, CAST(N'2020-12-19T00:00:00.000' AS DateTime), 1, 1)
SET IDENTITY_INSERT [dbo].[tblBooking] OFF
GO
SET IDENTITY_INSERT [dbo].[tblBookingDetail] ON 

INSERT [dbo].[tblBookingDetail] ([bookingDetailID], [bookingID], [roomID], [price], [checkInDate], [checkOutDate], [feedback]) VALUES (1, 1, 101, 400000, CAST(N'2020-12-12T00:00:00.000' AS DateTime), CAST(N'2020-12-16T00:00:00.000' AS DateTime), 4)
INSERT [dbo].[tblBookingDetail] ([bookingDetailID], [bookingID], [roomID], [price], [checkInDate], [checkOutDate], [feedback]) VALUES (3, 1, 202, 550000, CAST(N'2020-12-16T00:00:00.000' AS DateTime), CAST(N'2020-12-18T00:00:00.000' AS DateTime), 5)
INSERT [dbo].[tblBookingDetail] ([bookingDetailID], [bookingID], [roomID], [price], [checkInDate], [checkOutDate], [feedback]) VALUES (4, 3, 303, 1100000, CAST(N'2020-12-19T00:00:00.000' AS DateTime), CAST(N'2020-12-21T00:00:00.000' AS DateTime), NULL)
INSERT [dbo].[tblBookingDetail] ([bookingDetailID], [bookingID], [roomID], [price], [checkInDate], [checkOutDate], [feedback]) VALUES (5, 3, 404, 2500000, CAST(N'2020-12-13T00:00:00.000' AS DateTime), CAST(N'2020-12-25T00:00:00.000' AS DateTime), NULL)
INSERT [dbo].[tblBookingDetail] ([bookingDetailID], [bookingID], [roomID], [price], [checkInDate], [checkOutDate], [feedback]) VALUES (6, 2, 201, 550000, CAST(N'2020-12-12T00:00:00.000' AS DateTime), CAST(N'2020-12-14T00:00:00.000' AS DateTime), 1)
INSERT [dbo].[tblBookingDetail] ([bookingDetailID], [bookingID], [roomID], [price], [checkInDate], [checkOutDate], [feedback]) VALUES (7, 2, 301, 800000, CAST(N'2020-12-20T00:00:00.000' AS DateTime), CAST(N'2020-12-22T00:00:00.000' AS DateTime), NULL)
INSERT [dbo].[tblBookingDetail] ([bookingDetailID], [bookingID], [roomID], [price], [checkInDate], [checkOutDate], [feedback]) VALUES (8, 5, 103, 1200000, CAST(N'2020-12-29T00:00:00.000' AS DateTime), CAST(N'2021-01-01T00:00:00.000' AS DateTime), NULL)
INSERT [dbo].[tblBookingDetail] ([bookingDetailID], [bookingID], [roomID], [price], [checkInDate], [checkOutDate], [feedback]) VALUES (9, 5, 202, 1650000, CAST(N'2020-12-29T00:00:00.000' AS DateTime), CAST(N'2021-01-01T00:00:00.000' AS DateTime), NULL)
INSERT [dbo].[tblBookingDetail] ([bookingDetailID], [bookingID], [roomID], [price], [checkInDate], [checkOutDate], [feedback]) VALUES (10, 6, 402, 3000000, CAST(N'2020-12-24T00:00:00.000' AS DateTime), CAST(N'2020-12-26T00:00:00.000' AS DateTime), NULL)
INSERT [dbo].[tblBookingDetail] ([bookingDetailID], [bookingID], [roomID], [price], [checkInDate], [checkOutDate], [feedback]) VALUES (11, 6, 403, 5000000, CAST(N'2020-12-24T00:00:00.000' AS DateTime), CAST(N'2020-12-26T00:00:00.000' AS DateTime), NULL)
INSERT [dbo].[tblBookingDetail] ([bookingDetailID], [bookingID], [roomID], [price], [checkInDate], [checkOutDate], [feedback]) VALUES (12, 7, 201, 2200000, CAST(N'2020-12-20T00:00:00.000' AS DateTime), CAST(N'2020-12-24T00:00:00.000' AS DateTime), NULL)
INSERT [dbo].[tblBookingDetail] ([bookingDetailID], [bookingID], [roomID], [price], [checkInDate], [checkOutDate], [feedback]) VALUES (13, 7, 302, 4400000, CAST(N'2020-12-20T00:00:00.000' AS DateTime), CAST(N'2020-12-24T00:00:00.000' AS DateTime), NULL)
INSERT [dbo].[tblBookingDetail] ([bookingDetailID], [bookingID], [roomID], [price], [checkInDate], [checkOutDate], [feedback]) VALUES (14, 8, 101, 1600000, CAST(N'2020-12-20T00:00:00.000' AS DateTime), CAST(N'2020-12-24T00:00:00.000' AS DateTime), NULL)
INSERT [dbo].[tblBookingDetail] ([bookingDetailID], [bookingID], [roomID], [price], [checkInDate], [checkOutDate], [feedback]) VALUES (15, 8, 402, 6000000, CAST(N'2020-12-20T00:00:00.000' AS DateTime), CAST(N'2020-12-24T00:00:00.000' AS DateTime), NULL)
INSERT [dbo].[tblBookingDetail] ([bookingDetailID], [bookingID], [roomID], [price], [checkInDate], [checkOutDate], [feedback]) VALUES (16, 9, 301, 2400000, CAST(N'2020-12-29T00:00:00.000' AS DateTime), CAST(N'2021-01-01T00:00:00.000' AS DateTime), NULL)
INSERT [dbo].[tblBookingDetail] ([bookingDetailID], [bookingID], [roomID], [price], [checkInDate], [checkOutDate], [feedback]) VALUES (17, 10, 403, 5000000, CAST(N'2020-12-21T00:00:00.000' AS DateTime), CAST(N'2020-12-23T00:00:00.000' AS DateTime), NULL)
INSERT [dbo].[tblBookingDetail] ([bookingDetailID], [bookingID], [roomID], [price], [checkInDate], [checkOutDate], [feedback]) VALUES (18, 11, 301, 800000, CAST(N'2020-12-24T00:00:00.000' AS DateTime), CAST(N'2020-12-25T00:00:00.000' AS DateTime), NULL)
INSERT [dbo].[tblBookingDetail] ([bookingDetailID], [bookingID], [roomID], [price], [checkInDate], [checkOutDate], [feedback]) VALUES (19, 12, 403, 2500000, CAST(N'2020-12-29T00:00:00.000' AS DateTime), CAST(N'2020-12-30T00:00:00.000' AS DateTime), NULL)
INSERT [dbo].[tblBookingDetail] ([bookingDetailID], [bookingID], [roomID], [price], [checkInDate], [checkOutDate], [feedback]) VALUES (20, 13, 302, 1100000, CAST(N'2021-01-01T00:00:00.000' AS DateTime), CAST(N'2021-01-02T00:00:00.000' AS DateTime), NULL)
INSERT [dbo].[tblBookingDetail] ([bookingDetailID], [bookingID], [roomID], [price], [checkInDate], [checkOutDate], [feedback]) VALUES (21, 13, 303, 1100000, CAST(N'2021-01-01T00:00:00.000' AS DateTime), CAST(N'2021-01-02T00:00:00.000' AS DateTime), NULL)
SET IDENTITY_INSERT [dbo].[tblBookingDetail] OFF
GO
INSERT [dbo].[tblDiscountCode] ([discountID], [expiredDate], [percent]) VALUES (N'COLAMTHIMOICOAN', CAST(N'2020-12-17T00:00:00.000' AS DateTime), 0)
INSERT [dbo].[tblDiscountCode] ([discountID], [expiredDate], [percent]) VALUES (N'free101', CAST(N'2020-12-18T00:00:00.000' AS DateTime), 101)
INSERT [dbo].[tblDiscountCode] ([discountID], [expiredDate], [percent]) VALUES (N'free25', CAST(N'2020-12-25T00:00:00.000' AS DateTime), 25)
INSERT [dbo].[tblDiscountCode] ([discountID], [expiredDate], [percent]) VALUES (N'free50', CAST(N'2020-12-23T00:00:00.000' AS DateTime), 50)
INSERT [dbo].[tblDiscountCode] ([discountID], [expiredDate], [percent]) VALUES (N'free69', CAST(N'2020-12-30T00:00:00.000' AS DateTime), 69)
INSERT [dbo].[tblDiscountCode] ([discountID], [expiredDate], [percent]) VALUES (N'free75', CAST(N'2020-12-20T00:00:00.000' AS DateTime), 75)
INSERT [dbo].[tblDiscountCode] ([discountID], [expiredDate], [percent]) VALUES (N'free88', CAST(N'2020-12-20T00:00:00.000' AS DateTime), 88)
GO
INSERT [dbo].[tblRole] ([roleID], [roleName]) VALUES (1, N'ADMIN')
INSERT [dbo].[tblRole] ([roleID], [roleName]) VALUES (2, N'CUSTOMER')
GO
INSERT [dbo].[tblRoom] ([roomID], [roomTypeID], [status]) VALUES (101, 1, 1)
INSERT [dbo].[tblRoom] ([roomID], [roomTypeID], [status]) VALUES (102, 1, 1)
INSERT [dbo].[tblRoom] ([roomID], [roomTypeID], [status]) VALUES (103, 1, 1)
INSERT [dbo].[tblRoom] ([roomID], [roomTypeID], [status]) VALUES (201, 2, 1)
INSERT [dbo].[tblRoom] ([roomID], [roomTypeID], [status]) VALUES (202, 2, 1)
INSERT [dbo].[tblRoom] ([roomID], [roomTypeID], [status]) VALUES (203, 3, 1)
INSERT [dbo].[tblRoom] ([roomID], [roomTypeID], [status]) VALUES (301, 3, 1)
INSERT [dbo].[tblRoom] ([roomID], [roomTypeID], [status]) VALUES (302, 4, 1)
INSERT [dbo].[tblRoom] ([roomID], [roomTypeID], [status]) VALUES (303, 4, 1)
INSERT [dbo].[tblRoom] ([roomID], [roomTypeID], [status]) VALUES (401, 5, 1)
INSERT [dbo].[tblRoom] ([roomID], [roomTypeID], [status]) VALUES (402, 5, 1)
INSERT [dbo].[tblRoom] ([roomID], [roomTypeID], [status]) VALUES (403, 6, 1)
INSERT [dbo].[tblRoom] ([roomID], [roomTypeID], [status]) VALUES (404, 6, 0)
GO
INSERT [dbo].[tblRoomType] ([roomTypeID], [roomType], [price], [description]) VALUES (1, N'Single', 400000, N'A room assigned to one person.  Have one bed')
INSERT [dbo].[tblRoomType] ([roomTypeID], [roomType], [price], [description]) VALUES (2, N'Double', 550000, N'A room assigned to two people. May have one or more beds.')
INSERT [dbo].[tblRoomType] ([roomTypeID], [roomType], [price], [description]) VALUES (3, N'Quad', 800000, N'A room assigned to four people. May have two or more beds.')
INSERT [dbo].[tblRoomType] ([roomTypeID], [roomType], [price], [description]) VALUES (4, N'Queen', 1100000, N'A room with a queen-sized bed. May be occupied by one or more people.')
INSERT [dbo].[tblRoomType] ([roomTypeID], [roomType], [price], [description]) VALUES (5, N'King', 1500000, N'A room with a king-sized bed. May be occupied by one or more people.')
INSERT [dbo].[tblRoomType] ([roomTypeID], [roomType], [price], [description]) VALUES (6, N'Suite', 2500000, N'A parlour or living room connected to one or more bedrooms.')
GO
INSERT [dbo].[tblUser] ([email], [password], [roleID], [phone], [name], [address], [createdDate], [status]) VALUES (N'admin@gmail.com', N'1', 1, NULL, NULL, NULL, NULL, 1)
INSERT [dbo].[tblUser] ([email], [password], [roleID], [phone], [name], [address], [createdDate], [status]) VALUES (N'baopqse62518@fpt.edu.vn', N'1', 2, N'', N'', N'', CAST(N'2020-12-19T00:00:00.000' AS DateTime), 1)
INSERT [dbo].[tblUser] ([email], [password], [roleID], [phone], [name], [address], [createdDate], [status]) VALUES (N'cus1@gmail.com', N'1', 2, NULL, NULL, NULL, NULL, 1)
INSERT [dbo].[tblUser] ([email], [password], [roleID], [phone], [name], [address], [createdDate], [status]) VALUES (N'cus19@gmail.com', N'1', 2, N'0888777878', N'11', N'22', CAST(N'2020-12-17T00:00:00.000' AS DateTime), 1)
INSERT [dbo].[tblUser] ([email], [password], [roleID], [phone], [name], [address], [createdDate], [status]) VALUES (N'cus2@gmail.com', N'1', 2, NULL, NULL, NULL, NULL, 1)
INSERT [dbo].[tblUser] ([email], [password], [roleID], [phone], [name], [address], [createdDate], [status]) VALUES (N'cus22@gmail.com', N'1', 2, N'0879246503', N'alo', N'491/1 Huá»³nh VÄn BÃ¡nh', CAST(N'2020-12-19T00:00:00.000' AS DateTime), 1)
GO
SET IDENTITY_INSERT [dbo].[tblUserDiscount] ON 

INSERT [dbo].[tblUserDiscount] ([userDiscountID], [email], [discountID], [isUsed]) VALUES (1, N'cus1@gmail.com', N'free25', 1)
INSERT [dbo].[tblUserDiscount] ([userDiscountID], [email], [discountID], [isUsed]) VALUES (2, N'cus2@gmail.com', N'free69', 1)
INSERT [dbo].[tblUserDiscount] ([userDiscountID], [email], [discountID], [isUsed]) VALUES (3, N'cus2@gmail.com', N'Free50', 1)
INSERT [dbo].[tblUserDiscount] ([userDiscountID], [email], [discountID], [isUsed]) VALUES (4, N'baopqse62518@fpt.edu.vn', N'free25', 1)
INSERT [dbo].[tblUserDiscount] ([userDiscountID], [email], [discountID], [isUsed]) VALUES (5, N'baopqse62518@fpt.edu.vn', N'free50', 1)
INSERT [dbo].[tblUserDiscount] ([userDiscountID], [email], [discountID], [isUsed]) VALUES (6, N'baopqse62518@fpt.edu.vn', N'free69', 1)
INSERT [dbo].[tblUserDiscount] ([userDiscountID], [email], [discountID], [isUsed]) VALUES (7, N'baopqse62518@fpt.edu.vn', N'', 1)
INSERT [dbo].[tblUserDiscount] ([userDiscountID], [email], [discountID], [isUsed]) VALUES (8, N'cus2@gmail.com', N'', 1)
INSERT [dbo].[tblUserDiscount] ([userDiscountID], [email], [discountID], [isUsed]) VALUES (9, N'baopqse62518@fpt.edu.vn', N'', 1)
INSERT [dbo].[tblUserDiscount] ([userDiscountID], [email], [discountID], [isUsed]) VALUES (10, N'baopqse62518@fpt.edu.vn', N'free88', 1)
SET IDENTITY_INSERT [dbo].[tblUserDiscount] OFF
GO
USE [master]
GO
ALTER DATABASE [HotelBookingL002] SET  READ_WRITE 
GO
