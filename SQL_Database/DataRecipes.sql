Create database DatabaseRecipes 
go
USE [DatabaseRecipes]
GO
/****** Object:  Table [dbo].[Category]    Script Date: 30/11/2022 11:28:59 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](250) NOT NULL,
	[Image] [ntext] NULL,
	[Status] [int] NULL,
	[CreateDate] [date] NOT NULL,
	[CreateUser] [int] NOT NULL,
	[UpdateDate] [date] NULL,
	[UpdateUser] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Followers]    Script Date: 30/11/2022 11:28:59 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Followers](
	[UserId] [int] NULL,
	[FollowerId] [int] NULL,
	[Status] [int] NULL,
	[CreateDate] [date] NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Ingredient]    Script Date: 30/11/2022 11:28:59 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Ingredient](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[RecipeId] [int] NULL,
	[Name] [nvarchar](250) NOT NULL,
	[UnitOfMeasurement] [nvarchar](250) NOT NULL,
	[Status] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LoginDevice]    Script Date: 30/11/2022 11:28:59 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LoginDevice](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[DeviceName] [varchar](250) NOT NULL,
	[UserId] [int] NOT NULL,
	[Status] [int] NULL,
	[LastLoginDate] [date] NOT NULL,
	[LastLogoutDate] [date] NULL,
	[TokenDevice] [varchar](500) NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Notifications]    Script Date: 30/11/2022 11:28:59 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Notifications](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[UserId] [int] NULL,
	[NotificationId] [int] NULL,
	[Description] [ntext] NULL,
	[Status] [int] NULL,
	[CreateDate] [datetime] NOT NULL,
	[CreateUser] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NotificationType]    Script Date: 30/11/2022 11:28:59 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NotificationType](
	[Id] [int] NOT NULL,
	[Name] [nvarchar](250) NOT NULL,
	[Description] [ntext] NULL,
	[Status] [int] NULL,
	[CreateDate] [date] NOT NULL,
	[CreateUser] [int] NOT NULL,
	[UpdateDate] [date] NULL,
	[UpdateUser] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Rating]    Script Date: 30/11/2022 11:28:59 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Rating](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[RecipeId] [int] NULL,
	[UserId] [int] NULL,
	[Rating] [int] NOT NULL,
	[CreateDate] [date] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Recipes]    Script Date: 30/11/2022 11:28:59 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Recipes](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[CategoryId] [int] NULL,
	[AuthorId] [int] NULL,
	[Name] [nvarchar](250) NOT NULL,
	[Origin] [nvarchar](250) NULL,
	[Serves] [int] NULL,
	[Calories] [float] NULL,
	[Fat] [float] NULL,
	[Protein] [float] NULL,
	[Carbo] [float] NULL,
	[Image] [ntext] NULL,
	[TotalViews] [int] NULL,
	[CookTime] [nvarchar](250) NULL,
	[Status] [int] NULL,
	[CreateDate] [date] NOT NULL,
	[CreateUser] [int] NOT NULL,
	[UpdateDate] [date] NULL,
	[UpdateUser] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[RecipesSave]    Script Date: 30/11/2022 11:28:59 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[RecipesSave](
	[RecipeId] [int] NULL,
	[UserId] [int] NULL,
	[Status] [int] NULL,
	[CreateDate] [date] NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Steps]    Script Date: 30/11/2022 11:28:59 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Steps](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[RecipeId] [int] NOT NULL,
	[StepNumber] [int] NOT NULL,
	[Description] [ntext] NOT NULL,
	[Status] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Users]    Script Date: 30/11/2022 11:28:59 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[UserName] [nvarchar](250) NOT NULL,
	[DisplayName] [nvarchar](250) NOT NULL,
	[Sex] [int] NULL,
	[Address] [nvarchar](250) NULL,
	[PhoneNumber] [varchar](20) NOT NULL,
	[Password] [varchar](250) NOT NULL,
	[Email] [varchar](250) NULL,
	[Job] [nvarchar](250) NULL,
	[Role] [int] NOT NULL,
	[Avatar] [ntext] NULL,
	[Description] [ntext] NULL,
	[Status] [int] NULL,
	[CreateDate] [date] NOT NULL,
	[CreateUser] [int] NOT NULL,
	[UpdateDate] [date] NULL,
	[UpdateUser] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Category] ON 

INSERT [dbo].[Category] ([Id], [Name], [Image], [Status], [CreateDate], [CreateUser], [UpdateDate], [UpdateUser]) VALUES (1, N'Happy New Year', N'category/photo1.jpg', 0, CAST(N'2022-10-23' AS Date), 1, NULL, NULL)
INSERT [dbo].[Category] ([Id], [Name], [Image], [Status], [CreateDate], [CreateUser], [UpdateDate], [UpdateUser]) VALUES (2, N'Cake', N'category/photo12.jpg', 0, CAST(N'2022-10-23' AS Date), 1, NULL, NULL)
INSERT [dbo].[Category] ([Id], [Name], [Image], [Status], [CreateDate], [CreateUser], [UpdateDate], [UpdateUser]) VALUES (3, N'Vegetables', N'category/photo13.jpg', 0, CAST(N'2022-10-23' AS Date), 1, NULL, NULL)
INSERT [dbo].[Category] ([Id], [Name], [Image], [Status], [CreateDate], [CreateUser], [UpdateDate], [UpdateUser]) VALUES (4, N'Pastry', N'category/photo2.jpg', 0, CAST(N'2022-10-23' AS Date), 1, NULL, NULL)
INSERT [dbo].[Category] ([Id], [Name], [Image], [Status], [CreateDate], [CreateUser], [UpdateDate], [UpdateUser]) VALUES (5, N'Salad', N'category/photo3.jpg', 0, CAST(N'2022-11-11' AS Date), 1, NULL, NULL)
INSERT [dbo].[Category] ([Id], [Name], [Image], [Status], [CreateDate], [CreateUser], [UpdateDate], [UpdateUser]) VALUES (6, N'Breakfast', N'category/photo4.jpg', 0, CAST(N'2022-11-11' AS Date), 1, NULL, NULL)
INSERT [dbo].[Category] ([Id], [Name], [Image], [Status], [CreateDate], [CreateUser], [UpdateDate], [UpdateUser]) VALUES (7, N'Camping', N'category/photo5.jpg', 0, CAST(N'2022-11-11' AS Date), 1, NULL, NULL)
INSERT [dbo].[Category] ([Id], [Name], [Image], [Status], [CreateDate], [CreateUser], [UpdateDate], [UpdateUser]) VALUES (8, N'Party', N'category/photo6.jpg', 0, CAST(N'2022-11-11' AS Date), 1, NULL, NULL)
INSERT [dbo].[Category] ([Id], [Name], [Image], [Status], [CreateDate], [CreateUser], [UpdateDate], [UpdateUser]) VALUES (9, N'Chocolate', N'category/photo7.jpg', 0, CAST(N'2022-11-11' AS Date), 1, NULL, NULL)
INSERT [dbo].[Category] ([Id], [Name], [Image], [Status], [CreateDate], [CreateUser], [UpdateDate], [UpdateUser]) VALUES (10, N'Family', N'category/photo8.jpg', 0, CAST(N'2022-11-11' AS Date), 1, NULL, NULL)
INSERT [dbo].[Category] ([Id], [Name], [Image], [Status], [CreateDate], [CreateUser], [UpdateDate], [UpdateUser]) VALUES (11, N'Chinese Food', N'category/photo9.jpg', 0, CAST(N'2022-11-11' AS Date), 1, NULL, NULL)
INSERT [dbo].[Category] ([Id], [Name], [Image], [Status], [CreateDate], [CreateUser], [UpdateDate], [UpdateUser]) VALUES (12, N'Holiday', N'category/photo10.jpg', 0, CAST(N'2022-11-11' AS Date), 1, NULL, NULL)
INSERT [dbo].[Category] ([Id], [Name], [Image], [Status], [CreateDate], [CreateUser], [UpdateDate], [UpdateUser]) VALUES (13, N'Drink', N'category/photo11.jpg', 0, CAST(N'2022-11-11' AS Date), 1, NULL, NULL)
SET IDENTITY_INSERT [dbo].[Category] OFF
GO
INSERT [dbo].[Followers] ([UserId], [FollowerId], [Status], [CreateDate]) VALUES (22, 1, 0, CAST(N'2022-11-29' AS Date))
INSERT [dbo].[Followers] ([UserId], [FollowerId], [Status], [CreateDate]) VALUES (22, 2, 0, CAST(N'2022-11-29' AS Date))
INSERT [dbo].[Followers] ([UserId], [FollowerId], [Status], [CreateDate]) VALUES (22, 3, 0, CAST(N'2022-11-24' AS Date))
INSERT [dbo].[Followers] ([UserId], [FollowerId], [Status], [CreateDate]) VALUES (22, 4, 0, CAST(N'2022-11-29' AS Date))
INSERT [dbo].[Followers] ([UserId], [FollowerId], [Status], [CreateDate]) VALUES (22, 5, 1, CAST(N'2022-11-29' AS Date))
INSERT [dbo].[Followers] ([UserId], [FollowerId], [Status], [CreateDate]) VALUES (2, 22, 0, CAST(N'2022-11-24' AS Date))
INSERT [dbo].[Followers] ([UserId], [FollowerId], [Status], [CreateDate]) VALUES (4, 22, 0, CAST(N'2022-11-24' AS Date))
INSERT [dbo].[Followers] ([UserId], [FollowerId], [Status], [CreateDate]) VALUES (5, 22, 0, CAST(N'2022-11-24' AS Date))
INSERT [dbo].[Followers] ([UserId], [FollowerId], [Status], [CreateDate]) VALUES (19, 22, 0, CAST(N'2022-11-24' AS Date))
INSERT [dbo].[Followers] ([UserId], [FollowerId], [Status], [CreateDate]) VALUES (14, 22, 0, CAST(N'2022-11-24' AS Date))
INSERT [dbo].[Followers] ([UserId], [FollowerId], [Status], [CreateDate]) VALUES (24, 22, 0, CAST(N'2022-11-24' AS Date))
INSERT [dbo].[Followers] ([UserId], [FollowerId], [Status], [CreateDate]) VALUES (22, 22, 1, CAST(N'2022-11-29' AS Date))
GO
SET IDENTITY_INSERT [dbo].[Ingredient] ON 

INSERT [dbo].[Ingredient] ([Id], [RecipeId], [Name], [UnitOfMeasurement], [Status]) VALUES (1, 17, N'a1', N'100kg', 0)
INSERT [dbo].[Ingredient] ([Id], [RecipeId], [Name], [UnitOfMeasurement], [Status]) VALUES (2, 17, N'a2', N'500ml', 0)
INSERT [dbo].[Ingredient] ([Id], [RecipeId], [Name], [UnitOfMeasurement], [Status]) VALUES (3, 18, N'a1', N'100g', 0)
INSERT [dbo].[Ingredient] ([Id], [RecipeId], [Name], [UnitOfMeasurement], [Status]) VALUES (4, 18, N'a2', N'500ml', 0)
INSERT [dbo].[Ingredient] ([Id], [RecipeId], [Name], [UnitOfMeasurement], [Status]) VALUES (5, 19, N'a1', N'100g', 1)
INSERT [dbo].[Ingredient] ([Id], [RecipeId], [Name], [UnitOfMeasurement], [Status]) VALUES (6, 19, N'a2', N'500ml', 1)
INSERT [dbo].[Ingredient] ([Id], [RecipeId], [Name], [UnitOfMeasurement], [Status]) VALUES (7, 20, N'Noodles', N'300g', 1)
INSERT [dbo].[Ingredient] ([Id], [RecipeId], [Name], [UnitOfMeasurement], [Status]) VALUES (8, 20, N'Seafood', N'500kg', 1)
INSERT [dbo].[Ingredient] ([Id], [RecipeId], [Name], [UnitOfMeasurement], [Status]) VALUES (9, 20, N'Spice', N'100g', 1)
INSERT [dbo].[Ingredient] ([Id], [RecipeId], [Name], [UnitOfMeasurement], [Status]) VALUES (10, 19, N'a3', N'100g', 1)
INSERT [dbo].[Ingredient] ([Id], [RecipeId], [Name], [UnitOfMeasurement], [Status]) VALUES (11, 19, N'a4', N'400ml', 1)
INSERT [dbo].[Ingredient] ([Id], [RecipeId], [Name], [UnitOfMeasurement], [Status]) VALUES (12, 19, N'a3', N'100g', 0)
INSERT [dbo].[Ingredient] ([Id], [RecipeId], [Name], [UnitOfMeasurement], [Status]) VALUES (13, 19, N'a4', N'400ml', 0)
INSERT [dbo].[Ingredient] ([Id], [RecipeId], [Name], [UnitOfMeasurement], [Status]) VALUES (14, 20, N'Noodles', N'300g', 0)
INSERT [dbo].[Ingredient] ([Id], [RecipeId], [Name], [UnitOfMeasurement], [Status]) VALUES (15, 20, N'Seafood', N'500kg', 0)
INSERT [dbo].[Ingredient] ([Id], [RecipeId], [Name], [UnitOfMeasurement], [Status]) VALUES (16, 20, N'Spice', N'100g', 0)
INSERT [dbo].[Ingredient] ([Id], [RecipeId], [Name], [UnitOfMeasurement], [Status]) VALUES (17, 1, N'Ingri1', N'500kg', 0)
INSERT [dbo].[Ingredient] ([Id], [RecipeId], [Name], [UnitOfMeasurement], [Status]) VALUES (18, 1, N'Ingri2', N'100g', 0)
INSERT [dbo].[Ingredient] ([Id], [RecipeId], [Name], [UnitOfMeasurement], [Status]) VALUES (19, 1, N'Ingri3', N'300ml', 0)
INSERT [dbo].[Ingredient] ([Id], [RecipeId], [Name], [UnitOfMeasurement], [Status]) VALUES (20, 2, N'Ingri1', N'500kg', 0)
INSERT [dbo].[Ingredient] ([Id], [RecipeId], [Name], [UnitOfMeasurement], [Status]) VALUES (21, 2, N'Ingri2', N'100g', 0)
INSERT [dbo].[Ingredient] ([Id], [RecipeId], [Name], [UnitOfMeasurement], [Status]) VALUES (22, 2, N'Ingri3', N'300ml', 0)
INSERT [dbo].[Ingredient] ([Id], [RecipeId], [Name], [UnitOfMeasurement], [Status]) VALUES (23, 3, N'Ingri1', N'500kg', 0)
INSERT [dbo].[Ingredient] ([Id], [RecipeId], [Name], [UnitOfMeasurement], [Status]) VALUES (24, 3, N'Ingri2', N'100g', 0)
INSERT [dbo].[Ingredient] ([Id], [RecipeId], [Name], [UnitOfMeasurement], [Status]) VALUES (25, 3, N'Ingri3', N'300ml', 0)
INSERT [dbo].[Ingredient] ([Id], [RecipeId], [Name], [UnitOfMeasurement], [Status]) VALUES (26, 4, N'Ingri1', N'500kg', 0)
INSERT [dbo].[Ingredient] ([Id], [RecipeId], [Name], [UnitOfMeasurement], [Status]) VALUES (27, 4, N'Ingri2', N'100g', 0)
INSERT [dbo].[Ingredient] ([Id], [RecipeId], [Name], [UnitOfMeasurement], [Status]) VALUES (28, 4, N'Ingri3', N'300ml', 0)
INSERT [dbo].[Ingredient] ([Id], [RecipeId], [Name], [UnitOfMeasurement], [Status]) VALUES (29, 5, N'Ingri1', N'500kg', 0)
INSERT [dbo].[Ingredient] ([Id], [RecipeId], [Name], [UnitOfMeasurement], [Status]) VALUES (30, 5, N'Ingri2', N'100g', 0)
INSERT [dbo].[Ingredient] ([Id], [RecipeId], [Name], [UnitOfMeasurement], [Status]) VALUES (31, 5, N'Ingri3', N'300ml', 0)
INSERT [dbo].[Ingredient] ([Id], [RecipeId], [Name], [UnitOfMeasurement], [Status]) VALUES (32, 6, N'Ingri1', N'500kg', 0)
INSERT [dbo].[Ingredient] ([Id], [RecipeId], [Name], [UnitOfMeasurement], [Status]) VALUES (33, 6, N'Ingri2', N'100g', 0)
INSERT [dbo].[Ingredient] ([Id], [RecipeId], [Name], [UnitOfMeasurement], [Status]) VALUES (34, 6, N'Ingri3', N'300ml', 0)
INSERT [dbo].[Ingredient] ([Id], [RecipeId], [Name], [UnitOfMeasurement], [Status]) VALUES (35, 7, N'Ingri1', N'500kg', 0)
INSERT [dbo].[Ingredient] ([Id], [RecipeId], [Name], [UnitOfMeasurement], [Status]) VALUES (36, 7, N'Ingri2', N'300ml', 0)
INSERT [dbo].[Ingredient] ([Id], [RecipeId], [Name], [UnitOfMeasurement], [Status]) VALUES (37, 7, N'Ingri3', N'300ml', 0)
INSERT [dbo].[Ingredient] ([Id], [RecipeId], [Name], [UnitOfMeasurement], [Status]) VALUES (38, 8, N'Ingri1', N'500kg', 0)
INSERT [dbo].[Ingredient] ([Id], [RecipeId], [Name], [UnitOfMeasurement], [Status]) VALUES (39, 8, N'Ingri2', N'300ml', 0)
INSERT [dbo].[Ingredient] ([Id], [RecipeId], [Name], [UnitOfMeasurement], [Status]) VALUES (40, 8, N'Ingri3', N'300ml', 0)
INSERT [dbo].[Ingredient] ([Id], [RecipeId], [Name], [UnitOfMeasurement], [Status]) VALUES (41, 9, N'Ingri1', N'500kg', 0)
INSERT [dbo].[Ingredient] ([Id], [RecipeId], [Name], [UnitOfMeasurement], [Status]) VALUES (42, 9, N'Ingri2', N'300ml', 0)
INSERT [dbo].[Ingredient] ([Id], [RecipeId], [Name], [UnitOfMeasurement], [Status]) VALUES (43, 9, N'Ingri3', N'300ml', 0)
INSERT [dbo].[Ingredient] ([Id], [RecipeId], [Name], [UnitOfMeasurement], [Status]) VALUES (44, 10, N'Ingri1', N'500kg', 0)
INSERT [dbo].[Ingredient] ([Id], [RecipeId], [Name], [UnitOfMeasurement], [Status]) VALUES (45, 10, N'Ingri2', N'300ml', 0)
INSERT [dbo].[Ingredient] ([Id], [RecipeId], [Name], [UnitOfMeasurement], [Status]) VALUES (46, 10, N'Ingri3', N'300ml', 0)
INSERT [dbo].[Ingredient] ([Id], [RecipeId], [Name], [UnitOfMeasurement], [Status]) VALUES (47, 14, N'Ingri1', N'500kg', 0)
INSERT [dbo].[Ingredient] ([Id], [RecipeId], [Name], [UnitOfMeasurement], [Status]) VALUES (48, 14, N'Ingri2', N'300ml', 0)
INSERT [dbo].[Ingredient] ([Id], [RecipeId], [Name], [UnitOfMeasurement], [Status]) VALUES (49, 14, N'Ingri3', N'300ml', 0)
INSERT [dbo].[Ingredient] ([Id], [RecipeId], [Name], [UnitOfMeasurement], [Status]) VALUES (50, 15, N'Ingri1', N'500kg', 0)
INSERT [dbo].[Ingredient] ([Id], [RecipeId], [Name], [UnitOfMeasurement], [Status]) VALUES (51, 15, N'Ingri2', N'300ml', 0)
INSERT [dbo].[Ingredient] ([Id], [RecipeId], [Name], [UnitOfMeasurement], [Status]) VALUES (52, 15, N'Ingri3', N'300ml', 0)
INSERT [dbo].[Ingredient] ([Id], [RecipeId], [Name], [UnitOfMeasurement], [Status]) VALUES (53, 16, N'Ingri1', N'500kg', 0)
INSERT [dbo].[Ingredient] ([Id], [RecipeId], [Name], [UnitOfMeasurement], [Status]) VALUES (54, 16, N'Ingri2', N'300ml', 0)
INSERT [dbo].[Ingredient] ([Id], [RecipeId], [Name], [UnitOfMeasurement], [Status]) VALUES (55, 16, N'Ingri3', N'300ml', 0)
SET IDENTITY_INSERT [dbo].[Ingredient] OFF
GO
SET IDENTITY_INSERT [dbo].[LoginDevice] ON 

INSERT [dbo].[LoginDevice] ([Id], [DeviceName], [UserId], [Status], [LastLoginDate], [LastLogoutDate], [TokenDevice]) VALUES (1, N'a7789aaec5af4560', 22, 0, CAST(N'2022-11-24' AS Date), NULL, NULL)
INSERT [dbo].[LoginDevice] ([Id], [DeviceName], [UserId], [Status], [LastLoginDate], [LastLogoutDate], [TokenDevice]) VALUES (2, N'ad832950dd28fc34', 22, 0, CAST(N'2022-11-30' AS Date), NULL, NULL)
INSERT [dbo].[LoginDevice] ([Id], [DeviceName], [UserId], [Status], [LastLoginDate], [LastLogoutDate], [TokenDevice]) VALUES (3, N'ad832950dd28fc34', 24, 0, CAST(N'2022-11-22' AS Date), NULL, NULL)
INSERT [dbo].[LoginDevice] ([Id], [DeviceName], [UserId], [Status], [LastLoginDate], [LastLogoutDate], [TokenDevice]) VALUES (4, N'ABCDDD', 22, 0, CAST(N'2022-11-23' AS Date), NULL, NULL)
INSERT [dbo].[LoginDevice] ([Id], [DeviceName], [UserId], [Status], [LastLoginDate], [LastLogoutDate], [TokenDevice]) VALUES (5, N'e93504dd267eda38', 22, 0, CAST(N'2022-11-24' AS Date), NULL, NULL)
INSERT [dbo].[LoginDevice] ([Id], [DeviceName], [UserId], [Status], [LastLoginDate], [LastLogoutDate], [TokenDevice]) VALUES (6, N'b4880d2d3843fe40', 22, 0, CAST(N'2022-11-24' AS Date), NULL, NULL)
INSERT [dbo].[LoginDevice] ([Id], [DeviceName], [UserId], [Status], [LastLoginDate], [LastLogoutDate], [TokenDevice]) VALUES (7, N'4947bb2e38163a3a', 22, 0, CAST(N'2022-11-27' AS Date), NULL, NULL)
INSERT [dbo].[LoginDevice] ([Id], [DeviceName], [UserId], [Status], [LastLoginDate], [LastLogoutDate], [TokenDevice]) VALUES (8, N'd7ed4f55e548d9421', 22, 0, CAST(N'2022-11-30' AS Date), NULL, NULL)
INSERT [dbo].[LoginDevice] ([Id], [DeviceName], [UserId], [Status], [LastLoginDate], [LastLogoutDate], [TokenDevice]) VALUES (9, N'd7ed4f55e548d942', 22, 0, CAST(N'2022-11-30' AS Date), NULL, N'cvmzJMP4RzGbUshqiDan1N:APA91bF1dSNVV65ft1l5uaHGkJAp-RBStQqfudS4GJ-imzyUyr4EJNWUDEa1pUumcrpMeSu7_o_kPOPf5IHkK20Kgs9vPmNUVvDEB_itaCVKp3j3-Xt5hJZwJvaLnnQliw3xnVd5zZ8b')
SET IDENTITY_INSERT [dbo].[LoginDevice] OFF
GO
SET IDENTITY_INSERT [dbo].[Notifications] ON 

INSERT [dbo].[Notifications] ([Id], [UserId], [NotificationId], [Description], [Status], [CreateDate], [CreateUser]) VALUES (3, 22, 3, N'test', 1, CAST(N'2022-11-22T19:07:55.093' AS DateTime), 0)
INSERT [dbo].[Notifications] ([Id], [UserId], [NotificationId], [Description], [Status], [CreateDate], [CreateUser]) VALUES (4, 22, 2, N'test2', 0, CAST(N'2022-11-22T19:08:13.647' AS DateTime), 0)
INSERT [dbo].[Notifications] ([Id], [UserId], [NotificationId], [Description], [Status], [CreateDate], [CreateUser]) VALUES (6, 22, 4, N'test3', 1, CAST(N'2022-11-22T19:08:34.980' AS DateTime), 0)
INSERT [dbo].[Notifications] ([Id], [UserId], [NotificationId], [Description], [Status], [CreateDate], [CreateUser]) VALUES (7, 22, 1, N'test4', 0, CAST(N'2022-11-22T19:08:34.980' AS DateTime), 0)
INSERT [dbo].[Notifications] ([Id], [UserId], [NotificationId], [Description], [Status], [CreateDate], [CreateUser]) VALUES (8, 24, 3, N'I''m long posted a new recipe, please check it out with us!', 0, CAST(N'2022-11-27T22:24:50.327' AS DateTime), 0)
INSERT [dbo].[Notifications] ([Id], [UserId], [NotificationId], [Description], [Status], [CreateDate], [CreateUser]) VALUES (9, 5, 3, N'I''m long posted a new recipe, please check it out with us!', 0, CAST(N'2022-11-27T22:24:50.343' AS DateTime), 0)
INSERT [dbo].[Notifications] ([Id], [UserId], [NotificationId], [Description], [Status], [CreateDate], [CreateUser]) VALUES (10, 14, 3, N'I''m long posted a new recipe, please check it out with us!', 0, CAST(N'2022-11-27T22:24:50.347' AS DateTime), 0)
INSERT [dbo].[Notifications] ([Id], [UserId], [NotificationId], [Description], [Status], [CreateDate], [CreateUser]) VALUES (11, 19, 3, N'I''m long posted a new recipe, please check it out with us!', 0, CAST(N'2022-11-27T22:24:50.350' AS DateTime), 0)
INSERT [dbo].[Notifications] ([Id], [UserId], [NotificationId], [Description], [Status], [CreateDate], [CreateUser]) VALUES (12, 2, 3, N'I''m long posted a new recipe, please check it out with us!', 0, CAST(N'2022-11-27T22:24:50.350' AS DateTime), 0)
INSERT [dbo].[Notifications] ([Id], [UserId], [NotificationId], [Description], [Status], [CreateDate], [CreateUser]) VALUES (13, 4, 3, N'I''m long posted a new recipe, please check it out with us!', 0, CAST(N'2022-11-27T22:24:50.350' AS DateTime), 0)
INSERT [dbo].[Notifications] ([Id], [UserId], [NotificationId], [Description], [Status], [CreateDate], [CreateUser]) VALUES (14, 22, 4, N'I''m long has evaluated your Seafood fried noodles recipe!', 0, CAST(N'2022-11-28T23:11:58.030' AS DateTime), 0)
INSERT [dbo].[Notifications] ([Id], [UserId], [NotificationId], [Description], [Status], [CreateDate], [CreateUser]) VALUES (15, 22, 4, N'I''m long has evaluated your test2 recipe!', 0, CAST(N'2022-11-28T23:12:29.547' AS DateTime), 0)
INSERT [dbo].[Notifications] ([Id], [UserId], [NotificationId], [Description], [Status], [CreateDate], [CreateUser]) VALUES (16, 22, 4, N'I''m long has evaluated your test2 recipe!', 0, CAST(N'2022-11-28T23:14:12.583' AS DateTime), 0)
INSERT [dbo].[Notifications] ([Id], [UserId], [NotificationId], [Description], [Status], [CreateDate], [CreateUser]) VALUES (17, 4, 1, N'I''m long has been following you!', 0, CAST(N'2022-11-29T22:23:27.300' AS DateTime), 0)
INSERT [dbo].[Notifications] ([Id], [UserId], [NotificationId], [Description], [Status], [CreateDate], [CreateUser]) VALUES (18, 1, 1, N'I''m long has been following you!', 0, CAST(N'2022-11-29T22:23:48.390' AS DateTime), 0)
INSERT [dbo].[Notifications] ([Id], [UserId], [NotificationId], [Description], [Status], [CreateDate], [CreateUser]) VALUES (19, 2, 1, N'I''m long has been following you!', 0, CAST(N'2022-11-29T22:38:41.280' AS DateTime), 0)
INSERT [dbo].[Notifications] ([Id], [UserId], [NotificationId], [Description], [Status], [CreateDate], [CreateUser]) VALUES (20, 5, 1, N'I''m long has been following you!', 0, CAST(N'2022-11-29T22:46:51.183' AS DateTime), 0)
INSERT [dbo].[Notifications] ([Id], [UserId], [NotificationId], [Description], [Status], [CreateDate], [CreateUser]) VALUES (21, 22, 1, N'I''m long has been following you!', 0, CAST(N'2022-11-29T22:57:49.320' AS DateTime), 0)
INSERT [dbo].[Notifications] ([Id], [UserId], [NotificationId], [Description], [Status], [CreateDate], [CreateUser]) VALUES (22, 22, 1, N'I''m long has been following you!', 0, CAST(N'2022-11-29T22:57:50.943' AS DateTime), 0)
INSERT [dbo].[Notifications] ([Id], [UserId], [NotificationId], [Description], [Status], [CreateDate], [CreateUser]) VALUES (23, 22, 1, N'I''m long has been following you!', 0, CAST(N'2022-11-29T22:57:52.267' AS DateTime), 0)
INSERT [dbo].[Notifications] ([Id], [UserId], [NotificationId], [Description], [Status], [CreateDate], [CreateUser]) VALUES (24, 22, 1, N'I''m long has been following you!', 0, CAST(N'2022-11-29T22:57:53.663' AS DateTime), 0)
INSERT [dbo].[Notifications] ([Id], [UserId], [NotificationId], [Description], [Status], [CreateDate], [CreateUser]) VALUES (25, 22, 1, N'I''m long has been following you!', 0, CAST(N'2022-11-29T22:57:54.963' AS DateTime), 0)
INSERT [dbo].[Notifications] ([Id], [UserId], [NotificationId], [Description], [Status], [CreateDate], [CreateUser]) VALUES (26, 22, 4, N'I''m long has evaluated your test2 recipe!', 0, CAST(N'2022-11-30T00:19:41.893' AS DateTime), 0)
SET IDENTITY_INSERT [dbo].[Notifications] OFF
GO
INSERT [dbo].[NotificationType] ([Id], [Name], [Description], [Status], [CreateDate], [CreateUser], [UpdateDate], [UpdateUser]) VALUES (1, N'Follow', N'[userDisplay] has been following you!', 0, CAST(N'2022-11-17' AS Date), 0, CAST(N'2022-11-17' AS Date), 0)
INSERT [dbo].[NotificationType] ([Id], [Name], [Description], [Status], [CreateDate], [CreateUser], [UpdateDate], [UpdateUser]) VALUES (2, N'Update Recipe', N'The Recipe [recipeName] has been updated, please check it out with us!', 0, CAST(N'2022-11-17' AS Date), 0, CAST(N'2022-11-17' AS Date), 0)
INSERT [dbo].[NotificationType] ([Id], [Name], [Description], [Status], [CreateDate], [CreateUser], [UpdateDate], [UpdateUser]) VALUES (3, N'Create Recipe', N'[userDisplay] posted a new recipe, please check it out with us!', 0, CAST(N'2022-11-17' AS Date), 0, CAST(N'2022-11-17' AS Date), 0)
INSERT [dbo].[NotificationType] ([Id], [Name], [Description], [Status], [CreateDate], [CreateUser], [UpdateDate], [UpdateUser]) VALUES (4, N'Rating Recipe', N'[userDisplay] has evaluated your [recipeName] recipe!', 0, CAST(N'2022-11-17' AS Date), 0, CAST(N'2022-11-17' AS Date), 0)
INSERT [dbo].[NotificationType] ([Id], [Name], [Description], [Status], [CreateDate], [CreateUser], [UpdateDate], [UpdateUser]) VALUES (5, N'Other', N'Custom', 0, CAST(N'2022-11-17' AS Date), 0, CAST(N'2022-11-17' AS Date), 0)
GO
SET IDENTITY_INSERT [dbo].[Rating] ON 

INSERT [dbo].[Rating] ([Id], [RecipeId], [UserId], [Rating], [CreateDate]) VALUES (2, 1, 1, 3, CAST(N'1900-01-01' AS Date))
INSERT [dbo].[Rating] ([Id], [RecipeId], [UserId], [Rating], [CreateDate]) VALUES (3, 1, 1, 4, CAST(N'1900-01-01' AS Date))
INSERT [dbo].[Rating] ([Id], [RecipeId], [UserId], [Rating], [CreateDate]) VALUES (4, 1, 1, 5, CAST(N'1900-01-01' AS Date))
INSERT [dbo].[Rating] ([Id], [RecipeId], [UserId], [Rating], [CreateDate]) VALUES (5, 1, 1, 3, CAST(N'1900-01-01' AS Date))
INSERT [dbo].[Rating] ([Id], [RecipeId], [UserId], [Rating], [CreateDate]) VALUES (6, 20, 22, 3, CAST(N'2022-11-28' AS Date))
INSERT [dbo].[Rating] ([Id], [RecipeId], [UserId], [Rating], [CreateDate]) VALUES (7, 20, 22, 3, CAST(N'2022-11-28' AS Date))
INSERT [dbo].[Rating] ([Id], [RecipeId], [UserId], [Rating], [CreateDate]) VALUES (8, 19, 22, 5, CAST(N'2022-11-30' AS Date))
SET IDENTITY_INSERT [dbo].[Rating] OFF
GO
SET IDENTITY_INSERT [dbo].[Recipes] ON 

INSERT [dbo].[Recipes] ([Id], [CategoryId], [AuthorId], [Name], [Origin], [Serves], [Calories], [Fat], [Protein], [Carbo], [Image], [TotalViews], [CookTime], [Status], [CreateDate], [CreateUser], [UpdateDate], [UpdateUser]) VALUES (1, 1, 1, N'Roasted Pigeon salad', N'Viet Nam', 3, 3.5, 2.1, 12, 12, N'recipe/photo1.jpg', 28, N'30', 0, CAST(N'2022-10-25' AS Date), 1, NULL, NULL)
INSERT [dbo].[Recipes] ([Id], [CategoryId], [AuthorId], [Name], [Origin], [Serves], [Calories], [Fat], [Protein], [Carbo], [Image], [TotalViews], [CookTime], [Status], [CreateDate], [CreateUser], [UpdateDate], [UpdateUser]) VALUES (2, 2, 1, N'Young rice cake', N'England', 7, 3.5, 1.1, 11, 12, N'recipe/photo2.jpg', 109, N'40', 0, CAST(N'2022-10-25' AS Date), 1, NULL, NULL)
INSERT [dbo].[Recipes] ([Id], [CategoryId], [AuthorId], [Name], [Origin], [Serves], [Calories], [Fat], [Protein], [Carbo], [Image], [TotalViews], [CookTime], [Status], [CreateDate], [CreateUser], [UpdateDate], [UpdateUser]) VALUES (3, 2, 3, N'Fried roti Pigeon', N'Estonia', 7, 3.5, 1.1, 11, 12, N'recipe/photo3.jpg', 100, N'10', 0, CAST(N'2022-10-25' AS Date), 1, CAST(N'2022-11-17' AS Date), 0)
INSERT [dbo].[Recipes] ([Id], [CategoryId], [AuthorId], [Name], [Origin], [Serves], [Calories], [Fat], [Protein], [Carbo], [Image], [TotalViews], [CookTime], [Status], [CreateDate], [CreateUser], [UpdateDate], [UpdateUser]) VALUES (4, 2, 2, N'Egg fried rice', N'Finland', 7, 3.5, 1.1, 11, 12, N'recipe/photo4.jpg', 108, N'60', 0, CAST(N'2022-10-25' AS Date), 1, CAST(N'2022-11-17' AS Date), 0)
INSERT [dbo].[Recipes] ([Id], [CategoryId], [AuthorId], [Name], [Origin], [Serves], [Calories], [Fat], [Protein], [Carbo], [Image], [TotalViews], [CookTime], [Status], [CreateDate], [CreateUser], [UpdateDate], [UpdateUser]) VALUES (5, 2, 4, N'Spicy and sour soup', N'Iceland', 7, 3.5, 1.1, 11, 12, N'recipe/photo5.jpg', 103, N'20', 0, CAST(N'2022-10-25' AS Date), 1, CAST(N'2022-11-17' AS Date), 0)
INSERT [dbo].[Recipes] ([Id], [CategoryId], [AuthorId], [Name], [Origin], [Serves], [Calories], [Fat], [Protein], [Carbo], [Image], [TotalViews], [CookTime], [Status], [CreateDate], [CreateUser], [UpdateDate], [UpdateUser]) VALUES (6, 2, 1, N'Stuffed pancake', N'Norway', 7, 3.5, 1.1, 11, 12, N'recipe/photo6.jpg', 103, N'30', 0, CAST(N'2022-10-25' AS Date), 1, CAST(N'2022-11-17' AS Date), 0)
INSERT [dbo].[Recipes] ([Id], [CategoryId], [AuthorId], [Name], [Origin], [Serves], [Calories], [Fat], [Protein], [Carbo], [Image], [TotalViews], [CookTime], [Status], [CreateDate], [CreateUser], [UpdateDate], [UpdateUser]) VALUES (7, 4, 1, N'Shrimp in batter', N'Sweden', 7, 3.5, 1.1, 11, 12, N'recipe/photo7.jpg', 103, N'24', 0, CAST(N'2022-10-25' AS Date), 1, CAST(N'2022-11-17' AS Date), 0)
INSERT [dbo].[Recipes] ([Id], [CategoryId], [AuthorId], [Name], [Origin], [Serves], [Calories], [Fat], [Protein], [Carbo], [Image], [TotalViews], [CookTime], [Status], [CreateDate], [CreateUser], [UpdateDate], [UpdateUser]) VALUES (8, 1, 5, N'Girdle-cake', N'Germany', 7, 3.5, 1.1, 11, 12, N'recipe/photo8.jpg', 100, N'23', 0, CAST(N'2022-10-25' AS Date), 1, CAST(N'2022-11-17' AS Date), 0)
INSERT [dbo].[Recipes] ([Id], [CategoryId], [AuthorId], [Name], [Origin], [Serves], [Calories], [Fat], [Protein], [Carbo], [Image], [TotalViews], [CookTime], [Status], [CreateDate], [CreateUser], [UpdateDate], [UpdateUser]) VALUES (9, 2, 4, N'Pancako', N'France', 7, 3.5, 1.1, 11, 12, N'recipe/photo9.jpg', 101, N'45', 0, CAST(N'2022-10-25' AS Date), 1, CAST(N'2022-11-17' AS Date), 0)
INSERT [dbo].[Recipes] ([Id], [CategoryId], [AuthorId], [Name], [Origin], [Serves], [Calories], [Fat], [Protein], [Carbo], [Image], [TotalViews], [CookTime], [Status], [CreateDate], [CreateUser], [UpdateDate], [UpdateUser]) VALUES (10, 2, 4, N'Stuffed sticky rice balls', N'Austria', 7, 3.5, 1.1, 11, 12, N'recipe/photo10.jpg', 103, N'62', 0, CAST(N'2022-10-25' AS Date), 1, CAST(N'2022-11-17' AS Date), 0)
INSERT [dbo].[Recipes] ([Id], [CategoryId], [AuthorId], [Name], [Origin], [Serves], [Calories], [Fat], [Protein], [Carbo], [Image], [TotalViews], [CookTime], [Status], [CreateDate], [CreateUser], [UpdateDate], [UpdateUser]) VALUES (14, 5, 5, N'Juice', N'Spain', 7, 3.5, 1.1, 11, 12, N'recipe/photo11.jpg', 103, N'13', 0, CAST(N'2022-10-25' AS Date), 1, CAST(N'2022-11-17' AS Date), 0)
INSERT [dbo].[Recipes] ([Id], [CategoryId], [AuthorId], [Name], [Origin], [Serves], [Calories], [Fat], [Protein], [Carbo], [Image], [TotalViews], [CookTime], [Status], [CreateDate], [CreateUser], [UpdateDate], [UpdateUser]) VALUES (15, 2, 15, N'Beef dish', N'Bulgaria', 7, 3.5, 1.1, 11, 12, N'recipe/photo12.jpg', 103, N'35', 0, CAST(N'2022-10-25' AS Date), 1, CAST(N'2022-11-17' AS Date), 0)
INSERT [dbo].[Recipes] ([Id], [CategoryId], [AuthorId], [Name], [Origin], [Serves], [Calories], [Fat], [Protein], [Carbo], [Image], [TotalViews], [CookTime], [Status], [CreateDate], [CreateUser], [UpdateDate], [UpdateUser]) VALUES (16, 2, 16, N'Egg fried rice', N'Hungary', 7, 3.5, 1.1, 11, 12, N'recipe/photo13.jpg', 110, N'16', 0, CAST(N'2022-10-25' AS Date), 1, CAST(N'2022-11-17' AS Date), 0)
INSERT [dbo].[Recipes] ([Id], [CategoryId], [AuthorId], [Name], [Origin], [Serves], [Calories], [Fat], [Protein], [Carbo], [Image], [TotalViews], [CookTime], [Status], [CreateDate], [CreateUser], [UpdateDate], [UpdateUser]) VALUES (17, 1, 22, N'Spicy and sour soup', N'Chinese', 2, 2, 3, 1, 4, N'recipe/photo14.jpg', 6, N'30', 0, CAST(N'2022-11-22' AS Date), 22, CAST(N'2022-11-23' AS Date), 22)
INSERT [dbo].[Recipes] ([Id], [CategoryId], [AuthorId], [Name], [Origin], [Serves], [Calories], [Fat], [Protein], [Carbo], [Image], [TotalViews], [CookTime], [Status], [CreateDate], [CreateUser], [UpdateDate], [UpdateUser]) VALUES (18, 1, 22, N'Yuanyang hotpot', N'Thailand', 2, 2, 3, 1, 4, N'recipe/photo15.jpg', 2, N'30', 0, CAST(N'2022-11-23' AS Date), 22, CAST(N'2022-11-24' AS Date), 22)
INSERT [dbo].[Recipes] ([Id], [CategoryId], [AuthorId], [Name], [Origin], [Serves], [Calories], [Fat], [Protein], [Carbo], [Image], [TotalViews], [CookTime], [Status], [CreateDate], [CreateUser], [UpdateDate], [UpdateUser]) VALUES (19, 5, 22, N'Pizza', N'Lao', 22, 2, 3, 1, 4, N'recipe/photo16.jpg', 122, N'302', 0, CAST(N'2022-11-23' AS Date), 22, CAST(N'2022-11-28' AS Date), 22)
INSERT [dbo].[Recipes] ([Id], [CategoryId], [AuthorId], [Name], [Origin], [Serves], [Calories], [Fat], [Protein], [Carbo], [Image], [TotalViews], [CookTime], [Status], [CreateDate], [CreateUser], [UpdateDate], [UpdateUser]) VALUES (20, 2, 22, N'Seafood fried noodles', N'Vietnamese', 2, 10, 33, 50, 45, N'recipe/photo17.jpg', 115, N'40', 0, CAST(N'2022-11-27' AS Date), 22, CAST(N'2022-11-29' AS Date), 22)
SET IDENTITY_INSERT [dbo].[Recipes] OFF
GO
INSERT [dbo].[RecipesSave] ([RecipeId], [UserId], [Status], [CreateDate]) VALUES (1, 22, 1, CAST(N'2022-11-24' AS Date))
INSERT [dbo].[RecipesSave] ([RecipeId], [UserId], [Status], [CreateDate]) VALUES (2, 22, 0, CAST(N'2022-11-29' AS Date))
INSERT [dbo].[RecipesSave] ([RecipeId], [UserId], [Status], [CreateDate]) VALUES (3, 22, 0, CAST(N'2022-11-29' AS Date))
INSERT [dbo].[RecipesSave] ([RecipeId], [UserId], [Status], [CreateDate]) VALUES (7, 22, 0, CAST(N'2022-11-29' AS Date))
INSERT [dbo].[RecipesSave] ([RecipeId], [UserId], [Status], [CreateDate]) VALUES (9, 22, 0, CAST(N'2022-11-29' AS Date))
INSERT [dbo].[RecipesSave] ([RecipeId], [UserId], [Status], [CreateDate]) VALUES (15, 22, 0, CAST(N'2022-11-24' AS Date))
INSERT [dbo].[RecipesSave] ([RecipeId], [UserId], [Status], [CreateDate]) VALUES (19, 22, 0, CAST(N'2022-11-29' AS Date))
INSERT [dbo].[RecipesSave] ([RecipeId], [UserId], [Status], [CreateDate]) VALUES (16, 22, 0, CAST(N'2022-11-29' AS Date))
INSERT [dbo].[RecipesSave] ([RecipeId], [UserId], [Status], [CreateDate]) VALUES (4, 22, 0, CAST(N'2022-11-29' AS Date))
INSERT [dbo].[RecipesSave] ([RecipeId], [UserId], [Status], [CreateDate]) VALUES (17, 22, 0, CAST(N'2022-11-29' AS Date))
INSERT [dbo].[RecipesSave] ([RecipeId], [UserId], [Status], [CreateDate]) VALUES (10, 22, 0, CAST(N'2022-11-29' AS Date))
INSERT [dbo].[RecipesSave] ([RecipeId], [UserId], [Status], [CreateDate]) VALUES (20, 22, 0, CAST(N'2022-11-29' AS Date))
GO
SET IDENTITY_INSERT [dbo].[Steps] ON 

INSERT [dbo].[Steps] ([Id], [RecipeId], [StepNumber], [Description], [Status]) VALUES (2, 1, 1, N'Cook com', 0)
INSERT [dbo].[Steps] ([Id], [RecipeId], [StepNumber], [Description], [Status]) VALUES (3, 17, 1, N'aaaa', 0)
INSERT [dbo].[Steps] ([Id], [RecipeId], [StepNumber], [Description], [Status]) VALUES (4, 17, 2, N'bbbb', 0)
INSERT [dbo].[Steps] ([Id], [RecipeId], [StepNumber], [Description], [Status]) VALUES (5, 18, 1, N'aaa', 0)
INSERT [dbo].[Steps] ([Id], [RecipeId], [StepNumber], [Description], [Status]) VALUES (6, 18, 2, N'bbb', 0)
INSERT [dbo].[Steps] ([Id], [RecipeId], [StepNumber], [Description], [Status]) VALUES (7, 19, 1, N'aaa', 1)
INSERT [dbo].[Steps] ([Id], [RecipeId], [StepNumber], [Description], [Status]) VALUES (8, 19, 1, N'bbbbb', 0)
INSERT [dbo].[Steps] ([Id], [RecipeId], [StepNumber], [Description], [Status]) VALUES (9, 20, 1, N'For this dish, we used scallops, shrimp, and prepared frozen squid that was already cleaned and cut. You can also prepare fresh squid. Do this by cutting the cleaned squid open lengthwise and lightly scoring it with a diamond pattern.', 0)
INSERT [dbo].[Steps] ([Id], [RecipeId], [StepNumber], [Description], [Status]) VALUES (10, 20, 2, N'Bring a large pot or wok filled with water to a boil. If using fresh noodles, blanch them for 30-60 seconds.', 0)
INSERT [dbo].[Steps] ([Id], [RecipeId], [StepNumber], [Description], [Status]) VALUES (11, 20, 3, N'Bring 6 cups of water to a boil in your wok (you can also use the water you boiled the noodles in), and add a tablespoon of oil to the water. Blanch the choy sum for 30 seconds.', 0)
INSERT [dbo].[Steps] ([Id], [RecipeId], [StepNumber], [Description], [Status]) VALUES (12, 20, 4, N'Start with a clean, dry wok over medium low heat. Spread 1 tablespoon of oil around the perimeter of your wok. Add the ginger and cook for 10 to 20 seconds.', 0)
INSERT [dbo].[Steps] ([Id], [RecipeId], [StepNumber], [Description], [Status]) VALUES (13, 20, 5, N'Stir the cornstarch slurry to ensure it’s combined, and add ⅔ of it while stirring the contents of the wok. Cook for 15 seconds. The sauce should be thick enough to coat a spoon. Add the rest of the cornstarch slurry until the sauce is your preferred consistency.', 0)
INSERT [dbo].[Steps] ([Id], [RecipeId], [StepNumber], [Description], [Status]) VALUES (14, 19, 2, N'cccc', 0)
INSERT [dbo].[Steps] ([Id], [RecipeId], [StepNumber], [Description], [Status]) VALUES (15, 19, 3, N'kkkk', 0)
SET IDENTITY_INSERT [dbo].[Steps] OFF
GO
SET IDENTITY_INSERT [dbo].[Users] ON 

INSERT [dbo].[Users] ([Id], [UserName], [DisplayName], [Sex], [Address], [PhoneNumber], [Password], [Email], [Job], [Role], [Avatar], [Description], [Status], [CreateDate], [CreateUser], [UpdateDate], [UpdateUser]) VALUES (1, N'Tuyen.Vu', N'Vu Manh Tuyen', 1, N'Yen Bai', N'0787424822', N'jjadaskjdasfafkengewgwgwgsagsg', N'anh.vu3@sotatek.com', N'DEV C#', 1, N'user/Avatar4.jpg', N'I am favorite cooking', 0, CAST(N'2022-10-24' AS Date), 0, CAST(N'2022-11-26' AS Date), 0)
INSERT [dbo].[Users] ([Id], [UserName], [DisplayName], [Sex], [Address], [PhoneNumber], [Password], [Email], [Job], [Role], [Avatar], [Description], [Status], [CreateDate], [CreateUser], [UpdateDate], [UpdateUser]) VALUES (2, N'Duyen.pham', N'Duyen Pham', 0, N'Thai Binh', N'0787114822', N'sdgsadgsdg', N'duyen.pham@sotatek.com', N'DEV C#', 1, N'user/Avatar1.jpg', N'I am favorite cooking', 0, CAST(N'2022-10-28' AS Date), 0, CAST(N'1900-01-01' AS Date), 0)
INSERT [dbo].[Users] ([Id], [UserName], [DisplayName], [Sex], [Address], [PhoneNumber], [Password], [Email], [Job], [Role], [Avatar], [Description], [Status], [CreateDate], [CreateUser], [UpdateDate], [UpdateUser]) VALUES (3, N'My.nguyen', N'Thanh My', 1, N'Ha Tay', N'0787115522', N'asdg', N'my.nguyen@sotatek.com', N'DEV C#', 0, N'user/Avatar2.jpg', N'I am favorite cooking', 0, CAST(N'2022-10-28' AS Date), 1, CAST(N'1900-01-01' AS Date), 0)
INSERT [dbo].[Users] ([Id], [UserName], [DisplayName], [Sex], [Address], [PhoneNumber], [Password], [Email], [Job], [Role], [Avatar], [Description], [Status], [CreateDate], [CreateUser], [UpdateDate], [UpdateUser]) VALUES (4, N'Long.Tran', N'Quang Long', 1, N'Nam Dinh', N'0187115522', N'htrkgjvrtjnkrvtnj', N'long.tran@sotatek.com', N'DEV C#', 0, N'user/Avatar3.jpg', N'I am favorite cooking', 0, CAST(N'2022-10-28' AS Date), 1, CAST(N'1900-01-01' AS Date), 0)
INSERT [dbo].[Users] ([Id], [UserName], [DisplayName], [Sex], [Address], [PhoneNumber], [Password], [Email], [Job], [Role], [Avatar], [Description], [Status], [CreateDate], [CreateUser], [UpdateDate], [UpdateUser]) VALUES (5, N'A.Nguyen', N'Tien Dat', 1, N'Ha Noi', N'0987654321', N'112334343ssd', N'a.nguyen@gmail.com', N'PHP', 0, N'user/Avatar5.jpg', N'I am favorite cooking', 0, CAST(N'2022-11-17' AS Date), 0, CAST(N'2022-11-17' AS Date), 0)
INSERT [dbo].[Users] ([Id], [UserName], [DisplayName], [Sex], [Address], [PhoneNumber], [Password], [Email], [Job], [Role], [Avatar], [Description], [Status], [CreateDate], [CreateUser], [UpdateDate], [UpdateUser]) VALUES (14, N'B.Nguyen', N'Dang Thuy', 1, N'Ha Noi', N'0978675532', N'1123343ssd', N'b.nguyen@gmail.com', N'PHP', 0, N'user/Avatar6.jpg', N'I am favorite cooking', 0, CAST(N'2022-11-17' AS Date), 0, CAST(N'2022-11-17' AS Date), 0)
INSERT [dbo].[Users] ([Id], [UserName], [DisplayName], [Sex], [Address], [PhoneNumber], [Password], [Email], [Job], [Role], [Avatar], [Description], [Status], [CreateDate], [CreateUser], [UpdateDate], [UpdateUser]) VALUES (15, N'C.Nguyen', N'Vu Hang', 1, N'Ha Noi', N'0987897654', N'1123343ssd', N'c.nguyen@gmail.com', N'PHP', 0, N'user/Avatar3.jpg', N'I am favorite cooking', 0, CAST(N'2022-11-17' AS Date), 0, CAST(N'2022-11-17' AS Date), 0)
INSERT [dbo].[Users] ([Id], [UserName], [DisplayName], [Sex], [Address], [PhoneNumber], [Password], [Email], [Job], [Role], [Avatar], [Description], [Status], [CreateDate], [CreateUser], [UpdateDate], [UpdateUser]) VALUES (16, N'D.Nguyen', N'Kim Lien', 1, N'Ha Noi', N'0987897698', N'1123343ssd', N'd.nguyen@gmail.com', N'PHP', 0, N'user/Avatar.jpg', N'I am favorite cooking', 0, CAST(N'2022-11-17' AS Date), 0, CAST(N'2022-11-17' AS Date), 0)
INSERT [dbo].[Users] ([Id], [UserName], [DisplayName], [Sex], [Address], [PhoneNumber], [Password], [Email], [Job], [Role], [Avatar], [Description], [Status], [CreateDate], [CreateUser], [UpdateDate], [UpdateUser]) VALUES (17, N'E.Nguyen', N'Van Quyet', 1, N'Ha Noi', N'0987897678', N'11233ssd', N'e.nguyen@gmail.com', N'PHP', 0, N'user/Avatar4.jpg', N'I am favorite cooking', 0, CAST(N'2022-11-17' AS Date), 0, CAST(N'2022-11-17' AS Date), 0)
INSERT [dbo].[Users] ([Id], [UserName], [DisplayName], [Sex], [Address], [PhoneNumber], [Password], [Email], [Job], [Role], [Avatar], [Description], [Status], [CreateDate], [CreateUser], [UpdateDate], [UpdateUser]) VALUES (18, N'G.Nguyen', N'Ngoc Long', 1, N'Ha Noi', N'0987897345', N'1123343ssd', N'g.nguyen@gmail.com', N'PHP', 0, N'user/Avatar2.jpg', N'I am favorite cooking', 0, CAST(N'2022-11-17' AS Date), 0, CAST(N'2022-11-17' AS Date), 0)
INSERT [dbo].[Users] ([Id], [UserName], [DisplayName], [Sex], [Address], [PhoneNumber], [Password], [Email], [Job], [Role], [Avatar], [Description], [Status], [CreateDate], [CreateUser], [UpdateDate], [UpdateUser]) VALUES (19, N'H.Nguyen', N'Van Hung', 1, N'Ha Noi', N'0987897645', N'1123343ssd', N'h.nguyen@gmail.com', N'PHP', 0, N'user/Avatar1.jpg', N'I am favorite cooking', 0, CAST(N'2022-11-17' AS Date), 0, CAST(N'2022-11-17' AS Date), 0)
INSERT [dbo].[Users] ([Id], [UserName], [DisplayName], [Sex], [Address], [PhoneNumber], [Password], [Email], [Job], [Role], [Avatar], [Description], [Status], [CreateDate], [CreateUser], [UpdateDate], [UpdateUser]) VALUES (20, N'K.Nguyen', N'Hai Bien', 1, N'Ha Noi', N'0987897478', N'1123343ssd', N'k.nguyen@gmail.com', N'PHP', 0, N'user/Avatar5.jpg', N'I am favorite cooking', 0, CAST(N'2022-11-17' AS Date), 0, CAST(N'2022-11-17' AS Date), 0)
INSERT [dbo].[Users] ([Id], [UserName], [DisplayName], [Sex], [Address], [PhoneNumber], [Password], [Email], [Job], [Role], [Avatar], [Description], [Status], [CreateDate], [CreateUser], [UpdateDate], [UpdateUser]) VALUES (21, N'L.Nguyen', N'Duy Te', 1, N'Ha Noi', N'0987897651', N'1123343ssd', N'l.nguyen@gmail.com', N'PHP', 0, N'user/Avatar2.jpg', N'I am favorite cooking', 0, CAST(N'2022-11-17' AS Date), 0, CAST(N'2022-11-17' AS Date), 0)
INSERT [dbo].[Users] ([Id], [UserName], [DisplayName], [Sex], [Address], [PhoneNumber], [Password], [Email], [Job], [Role], [Avatar], [Description], [Status], [CreateDate], [CreateUser], [UpdateDate], [UpdateUser]) VALUES (22, N'ahihi', N'I''m long', 1, N'Vietnamese', N'0988932076', N'25f9e794323b453885f5181f1b624d0b', N'ahihi@gmail.com', N'Chef', 0, N'user/Avatar7.jpg', N'Some people may think cooking is just a daily work to make meals. But for me, cooking is a hobby – the best hobby to have, because it can be very useful in life.', 0, CAST(N'2022-11-18' AS Date), 0, CAST(N'2022-11-27' AS Date), 22)
INSERT [dbo].[Users] ([Id], [UserName], [DisplayName], [Sex], [Address], [PhoneNumber], [Password], [Email], [Job], [Role], [Avatar], [Description], [Status], [CreateDate], [CreateUser], [UpdateDate], [UpdateUser]) VALUES (23, N'Tuyen.Vu3', N'Van Tuyen', 0, N'Yen Bai', N'0380557990', N'e10adc3949ba59abbe56e057f20f883e', N'tuyen.vu@ssotatek.com', N'Chef', 0, N'user/Avatar4.jpg', N'I am favorite cooking', 0, CAST(N'2022-11-22' AS Date), 0, CAST(N'2022-11-22' AS Date), 0)
INSERT [dbo].[Users] ([Id], [UserName], [DisplayName], [Sex], [Address], [PhoneNumber], [Password], [Email], [Job], [Role], [Avatar], [Description], [Status], [CreateDate], [CreateUser], [UpdateDate], [UpdateUser]) VALUES (24, N'datte', N'Quang Dat', 0, N'Nam Dinh, Viet Nam', N'0988932079', N'25d55ad283aa400af464c76d713c07ad', N'datte@gmail.com', N'Chef', 0, N'', N'I am favorite cooking', 0, CAST(N'2022-11-22' AS Date), 0, CAST(N'2022-11-22' AS Date), 0)
SET IDENTITY_INSERT [dbo].[Users] OFF
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__Users__85FB4E384640C991]    Script Date: 30/11/2022 11:29:00 AM ******/
ALTER TABLE [dbo].[Users] ADD UNIQUE NONCLUSTERED 
(
	[PhoneNumber] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__Users__C9F284567491415E]    Script Date: 30/11/2022 11:29:00 AM ******/
ALTER TABLE [dbo].[Users] ADD UNIQUE NONCLUSTERED 
(
	[UserName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Category] ADD  DEFAULT ((0)) FOR [Status]
GO
ALTER TABLE [dbo].[Category] ADD  DEFAULT (getdate()) FOR [CreateDate]
GO
ALTER TABLE [dbo].[Category] ADD  DEFAULT ((0)) FOR [CreateUser]
GO
ALTER TABLE [dbo].[Category] ADD  DEFAULT (getdate()) FOR [UpdateDate]
GO
ALTER TABLE [dbo].[Category] ADD  DEFAULT ((0)) FOR [UpdateUser]
GO
ALTER TABLE [dbo].[Followers] ADD  DEFAULT ((0)) FOR [Status]
GO
ALTER TABLE [dbo].[Followers] ADD  DEFAULT (getdate()) FOR [CreateDate]
GO
ALTER TABLE [dbo].[Ingredient] ADD  DEFAULT ((0)) FOR [Status]
GO
ALTER TABLE [dbo].[LoginDevice] ADD  DEFAULT ((0)) FOR [Status]
GO
ALTER TABLE [dbo].[LoginDevice] ADD  DEFAULT (getdate()) FOR [LastLoginDate]
GO
ALTER TABLE [dbo].[Notifications] ADD  DEFAULT ((0)) FOR [Status]
GO
ALTER TABLE [dbo].[Notifications] ADD  DEFAULT (getdate()) FOR [CreateDate]
GO
ALTER TABLE [dbo].[Notifications] ADD  DEFAULT ((0)) FOR [CreateUser]
GO
ALTER TABLE [dbo].[NotificationType] ADD  DEFAULT ((0)) FOR [Status]
GO
ALTER TABLE [dbo].[NotificationType] ADD  DEFAULT (getdate()) FOR [CreateDate]
GO
ALTER TABLE [dbo].[NotificationType] ADD  DEFAULT ((0)) FOR [CreateUser]
GO
ALTER TABLE [dbo].[NotificationType] ADD  DEFAULT (getdate()) FOR [UpdateDate]
GO
ALTER TABLE [dbo].[NotificationType] ADD  DEFAULT ((0)) FOR [UpdateUser]
GO
ALTER TABLE [dbo].[Rating] ADD  DEFAULT ((5)) FOR [Rating]
GO
ALTER TABLE [dbo].[Rating] ADD  DEFAULT (getdate()) FOR [CreateDate]
GO
ALTER TABLE [dbo].[Recipes] ADD  DEFAULT ((1)) FOR [TotalViews]
GO
ALTER TABLE [dbo].[Recipes] ADD  DEFAULT ((0)) FOR [Status]
GO
ALTER TABLE [dbo].[Recipes] ADD  DEFAULT (getdate()) FOR [CreateDate]
GO
ALTER TABLE [dbo].[Recipes] ADD  DEFAULT ((0)) FOR [CreateUser]
GO
ALTER TABLE [dbo].[Recipes] ADD  DEFAULT (getdate()) FOR [UpdateDate]
GO
ALTER TABLE [dbo].[Recipes] ADD  DEFAULT ((0)) FOR [UpdateUser]
GO
ALTER TABLE [dbo].[RecipesSave] ADD  DEFAULT ((0)) FOR [Status]
GO
ALTER TABLE [dbo].[RecipesSave] ADD  DEFAULT (getdate()) FOR [CreateDate]
GO
ALTER TABLE [dbo].[Steps] ADD  DEFAULT ((1)) FOR [StepNumber]
GO
ALTER TABLE [dbo].[Steps] ADD  DEFAULT ((0)) FOR [Status]
GO
ALTER TABLE [dbo].[Users] ADD  DEFAULT ((0)) FOR [Sex]
GO
ALTER TABLE [dbo].[Users] ADD  DEFAULT ((0)) FOR [Role]
GO
ALTER TABLE [dbo].[Users] ADD  DEFAULT ((0)) FOR [Status]
GO
ALTER TABLE [dbo].[Users] ADD  DEFAULT (getdate()) FOR [CreateDate]
GO
ALTER TABLE [dbo].[Users] ADD  DEFAULT ((0)) FOR [CreateUser]
GO
ALTER TABLE [dbo].[Users] ADD  DEFAULT (getdate()) FOR [UpdateDate]
GO
ALTER TABLE [dbo].[Users] ADD  DEFAULT ((0)) FOR [UpdateUser]
GO
ALTER TABLE [dbo].[Followers]  WITH CHECK ADD FOREIGN KEY([FollowerId])
REFERENCES [dbo].[Users] ([Id])
GO
ALTER TABLE [dbo].[Followers]  WITH CHECK ADD FOREIGN KEY([UserId])
REFERENCES [dbo].[Users] ([Id])
GO
ALTER TABLE [dbo].[Ingredient]  WITH CHECK ADD FOREIGN KEY([RecipeId])
REFERENCES [dbo].[Recipes] ([Id])
GO
ALTER TABLE [dbo].[LoginDevice]  WITH CHECK ADD FOREIGN KEY([UserId])
REFERENCES [dbo].[Users] ([Id])
GO
ALTER TABLE [dbo].[Notifications]  WITH CHECK ADD FOREIGN KEY([NotificationId])
REFERENCES [dbo].[NotificationType] ([Id])
GO
ALTER TABLE [dbo].[Notifications]  WITH CHECK ADD FOREIGN KEY([UserId])
REFERENCES [dbo].[Users] ([Id])
GO
ALTER TABLE [dbo].[Rating]  WITH CHECK ADD FOREIGN KEY([RecipeId])
REFERENCES [dbo].[Recipes] ([Id])
GO
ALTER TABLE [dbo].[Rating]  WITH CHECK ADD FOREIGN KEY([UserId])
REFERENCES [dbo].[Users] ([Id])
GO
ALTER TABLE [dbo].[Recipes]  WITH CHECK ADD FOREIGN KEY([AuthorId])
REFERENCES [dbo].[Users] ([Id])
GO
ALTER TABLE [dbo].[Recipes]  WITH CHECK ADD FOREIGN KEY([CategoryId])
REFERENCES [dbo].[Category] ([Id])
GO
ALTER TABLE [dbo].[RecipesSave]  WITH CHECK ADD FOREIGN KEY([RecipeId])
REFERENCES [dbo].[Recipes] ([Id])
GO
ALTER TABLE [dbo].[RecipesSave]  WITH CHECK ADD FOREIGN KEY([UserId])
REFERENCES [dbo].[Users] ([Id])
GO
ALTER TABLE [dbo].[Steps]  WITH CHECK ADD FOREIGN KEY([RecipeId])
REFERENCES [dbo].[Recipes] ([Id])
GO