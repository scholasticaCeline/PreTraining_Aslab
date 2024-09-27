USE [master]
GO
/****** Object:  Database [BingsuUniversity]    Script Date: 5/17/2024 12:25:04 PM ******/
CREATE DATABASE [BingsuUniversity]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'BingsuUniversity', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\BingsuUniversity.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'BingsuUniversity_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\BingsuUniversity_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [BingsuUniversity] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [BingsuUniversity].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [BingsuUniversity] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [BingsuUniversity] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [BingsuUniversity] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [BingsuUniversity] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [BingsuUniversity] SET ARITHABORT OFF 
GO
ALTER DATABASE [BingsuUniversity] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [BingsuUniversity] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [BingsuUniversity] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [BingsuUniversity] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [BingsuUniversity] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [BingsuUniversity] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [BingsuUniversity] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [BingsuUniversity] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [BingsuUniversity] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [BingsuUniversity] SET  ENABLE_BROKER 
GO
ALTER DATABASE [BingsuUniversity] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [BingsuUniversity] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [BingsuUniversity] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [BingsuUniversity] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [BingsuUniversity] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [BingsuUniversity] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [BingsuUniversity] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [BingsuUniversity] SET RECOVERY FULL 
GO
ALTER DATABASE [BingsuUniversity] SET  MULTI_USER 
GO
ALTER DATABASE [BingsuUniversity] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [BingsuUniversity] SET DB_CHAINING OFF 
GO
ALTER DATABASE [BingsuUniversity] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [BingsuUniversity] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [BingsuUniversity] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [BingsuUniversity] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'BingsuUniversity', N'ON'
GO
ALTER DATABASE [BingsuUniversity] SET QUERY_STORE = ON
GO
ALTER DATABASE [BingsuUniversity] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [BingsuUniversity]
GO
/****** Object:  Table [dbo].[Class]    Script Date: 5/17/2024 12:25:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Class](
	[classID] [char](5) NOT NULL,
	[courseID] [char](7) NOT NULL,
	[lecturerID] [char](7) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[classID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Course]    Script Date: 5/17/2024 12:25:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Course](
	[courseID] [char](7) NOT NULL,
	[courseName] [varchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[courseID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Lecturer]    Script Date: 5/17/2024 12:25:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Lecturer](
	[lecturerID] [char](7) NOT NULL,
	[lecturerName] [varchar](255) NOT NULL,
	[lecturerEmail] [varchar](255) NOT NULL,
	[lecturerPhone] [varchar](255) NOT NULL,
	[lecturerDOB] [date] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[lecturerID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Students]    Script Date: 5/17/2024 12:25:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Students](
	[StudentID] [char](9) NOT NULL,
	[studentName] [varchar](255) NOT NULL,
	[studentEmail] [varchar](255) NULL,
	[studentPhone] [varchar](15) NULL,
	[studentDOB] [date] NOT NULL,
	[classID] [char](5) NULL,
PRIMARY KEY CLUSTERED 
(
	[StudentID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Lecturer] ([lecturerID], [lecturerName], [lecturerEmail], [lecturerPhone], [lecturerDOB]) VALUES (N'LEC0001', N'Vernandio', N'Vernandio@gmail.com', N'0812345678', CAST(N'2000-01-01' AS Date))
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__Lecturer__6C3E5E0933E4E34A]    Script Date: 5/17/2024 12:25:05 PM ******/
ALTER TABLE [dbo].[Lecturer] ADD UNIQUE NONCLUSTERED 
(
	[lecturerEmail] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__Lecturer__9A8562E8F46EDF8F]    Script Date: 5/17/2024 12:25:05 PM ******/
ALTER TABLE [dbo].[Lecturer] ADD UNIQUE NONCLUSTERED 
(
	[lecturerPhone] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Class]  WITH CHECK ADD FOREIGN KEY([courseID])
REFERENCES [dbo].[Course] ([courseID])
GO
ALTER TABLE [dbo].[Class]  WITH CHECK ADD FOREIGN KEY([lecturerID])
REFERENCES [dbo].[Lecturer] ([lecturerID])
GO
ALTER TABLE [dbo].[Students]  WITH CHECK ADD FOREIGN KEY([classID])
REFERENCES [dbo].[Class] ([classID])
GO
ALTER TABLE [dbo].[Lecturer]  WITH CHECK ADD CHECK  (([lecturerID] like 'LEC[0-9][0-9][0-9][0-9]'))
GO
ALTER TABLE [dbo].[Lecturer]  WITH CHECK ADD CHECK  (([lecturerPhone] like '[0-9]%'))
GO
USE [master]
GO
ALTER DATABASE [BingsuUniversity] SET  READ_WRITE 
GO
