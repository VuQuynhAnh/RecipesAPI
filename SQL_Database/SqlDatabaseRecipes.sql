Create database DatabaseRecipes 
go
use DatabaseRecipes
go

Create table Users -- Người dùng
(
	Id int primary key identity,
	UserName nvarchar(250) unique not null,
	DisplayName nvarchar(250) not null,
	Sex int default 0,
	Address nvarchar(250),
	PhoneNumber varchar(20) not null unique,
	Password varchar(250) not null,
	Email varchar(250),
	Job nvarchar(250),
	Role int not null default 0,
	Avatar varchar(250),
	Description ntext,
	Status int default 0,
	CreateDate date not null default getdate(),
	CreateUser int not null default 0,
	UpdateDate date,
	UpdateUser int
)
go

Create table LoginDevice -- Thiết bị đăng nhập
(
	Id int primary key identity,
	DeviceName varchar(250) not null,
	UserId int foreign key references Users(Id) not null,
	Status int default 0,
	LastLoginDate date not null,
	LastLogoutDate date,
)
go

Create table Category -- Danh mục
(
	Id int primary key identity,
	Name nvarchar(250) not null,
	Status int default 0,
	CreateDate date not null default getdate(),
	CreateUser int not null default 0,
	UpdateDate date,
	UpdateUser int
)
go

Create table Recipes -- Công thức
(
	Id int primary key identity,
	CategoryId int foreign key references Category(Id),
	AuthorId int foreign key references Users(Id),
	Name nvarchar(250) not null,
	Origin nvarchar(250),
	Serves int,
	Image ntext,
	TotalViews int default 1,
	CookTime nvarchar(250),
	Status int default 0,
	CreateDate date not null default getdate(),
	CreateUser int not null default 0,
	UpdateDate date,
	UpdateUser int
)
go

Create table Ingredient -- Bảng nguyên liệu
(
	Id int primary key identity,
	RecipeId int foreign key references Recipes(Id),
	Name nvarchar(250) not null,
	UnitOfMeasurement nvarchar(250) not null,
	Status int default 0
)
go

Create table Steps -- Bước thực hiện
(
	Id int primary key identity,
	RecipeId int not null foreign key references Recipes(Id),
	StepNumber int not null default 1,
	Description ntext not null,
	Status int default 0
)
go

Create table Rating
(
	Id int primary key identity,
	RecipeId int foreign key references Recipes(Id),
	UserId int foreign key references Users(Id),
	Rating int not null default 5,
	CreateDate date not null default getdate(),
)
go

Create table RecipesSave
(
	RecipeId int foreign key references Recipes(Id),
	UserId int foreign key references Users(Id),
	Status int default 0,
	CreateDate date not null default getdate()
)
go

Create table Followers
(
	UserId int foreign key references Users(Id),
	FollowerId int foreign key references Users(Id),
	Status int default 0,
	CreateDate date not null default getdate()
)
go

Create table NotificationType
(
	Id int primary key identity,
	Name nvarchar(250) not null,
	Description ntext,
	Status int default 0,
	CreateDate date not null default getdate()
)
go

Create table Notifications
(
	Id int primary key identity,
	UserId int foreign key references Users(Id),
	NotificationId int foreign key references NotificationType(Id),
	Description ntext,
	Status int default 0,
	CreateDate date not null default getdate(),
	CreateUser int not null default 0
)
go

--Insert data temp

insert into Category values
(N'Đồ Việt Nam', 0, '2022-10-23', 1, null, null),
(N'Đồ Trung Quốc', 1, '2022-10-23', 1, null, null)
go

insert into Users values (N'Anh.vu3', N'Vũ Quỳnh Anh', 1, N'Yên Bái', '0787424822', 'jjadaskjdasfafkengewgwgwgsagsg', 'anh.vu3@sotatek.com', 'DEV C#', 1, 'image', N'Người phát triển API', 0, '2022/10/24', 0, '', 0)
go
insert into Users values (N'Duyen.pham', N'Phạm Thị Duyên', 0, N'Thái Bình', '0787114822', 'sdgsadgsdg', 'duyen.pham@sotatek.com', 'DEV C#', 1, 'image', N'Người phát triển API', 0, '2022/10/28', 0, '', 0)
go
insert into Users values (N'My.nguyen', N'Nguyễn Thanh Mỹ', 1, N'Hà Tây', '0787115522', 'asdg', 'my.nguyen@sotatek.com', 'DEV C#', 0, 'image', N'Người phát triển API', 0, '2022/10/28', 1, '', 0)
go
insert into Users values (N'Long.Tran', N'Trần Quang Long', 1, N'Nam Định', '0187115522', 'htrkgjvrtjnkrvtnj', 'long.tran@sotatek.com', 'DEV C#', 0, 'image', N'Người phát triển API', 0, '2022/10/28', 1, '', 0)
go

insert into Recipes values(1,1,'Rice','Viet Nam', 3, '',1,'',0,'2022-10-25',1,null,null)
go
insert into Recipes values(2,1,'Fist','Thai Lan', 7, '',100,'',1,'2022-10-25',1,null,null)
go

insert into Steps values (1, 1, 'Cook com', 0)
go

-- Proc Category
create proc GetAllCategory
as
select 
	cat.*,
	createUser.UserName as CreateUserDisplay,
	updateUser.UserName as UpdateUserDisplay
from Category cat
left join Users as createUser on cat.CreateUser = createUser.Id
left join Users as updateUser on cat.UpdateUser = updateUser.Id
go

create proc FilterListCategory
	@keyword nvarchar(250),
	@isGetAll bit
as
begin
	select 
		cat.*,
		createUser.UserName as CreateUserDisplay,
		updateUser.UserName as UpdateUserDisplay
	from Category cat
	left join Users as createUser on cat.CreateUser = createUser.Id
	left join Users as updateUser on cat.UpdateUser = updateUser.Id
	where
		cat.Name like N'%' + @keyword + '%'
		and (@isGetAll = 1 or cat.Status = 0)
end
go

create proc GetCategoryById
	@id int
as
	select 
		cat.*,
		createUser.UserName as CreateUserDisplay,
		updateUser.UserName as UpdateUserDisplay
	from Category cat
	left join Users as createUser on cat.CreateUser = createUser.Id
	left join Users as updateUser on cat.UpdateUser = updateUser.Id
	where cat.Id = @id
go

-- Proc Recipes
	
create proc GetAllRecipes
as
select 
	recipe.*,
	cat.Name as CategoryDisplay,
	author.UserName as Author,
	createUser.UserName as CreateUserDisplay,
	updateUser.UserName as UpdateUserDisplay
from Recipes recipe
left join Users as createUser on recipe.CreateUser = createUser.Id
left join Users as updateUser on recipe.UpdateUser = updateUser.Id
left join Users as author on recipe.AuthorId = author.Id
left join Category as cat on recipe.CategoryId = cat.Id
go

create proc GetSaveRecipes
	@userId int
as
select 
	recipe.*,
	cat.Name as CategoryDisplay,
	author.UserName as Author,
	createUser.UserName as CreateUserDisplay,
	updateUser.UserName as UpdateUserDisplay,
	recipeSave.CreateDate as SaveDate
from RecipesSave as recipeSave
left join Recipes as recipe on recipe.Id = recipeSave.RecipeId
left join Users as createUser on recipe.CreateUser = createUser.Id
left join Users as updateUser on recipe.UpdateUser = updateUser.Id
left join Users as author on recipe.AuthorId = author.Id
left join Category as cat on recipe.CategoryId = cat.Id
where recipe.Status = 0 and recipeSave.UserId = @userId
go

create proc FilterListRecipes
	@keyword nvarchar(250),
	@catId int,
	@authorId int,
	@name nvarchar(250),
	@origin nvarchar(250),
	@minServer int,
	@maxServer int,
	@minTotalViews int,
	@maxTotalViews int,
	@cookTime nvarchar(250),
	@status int
as
select 
	recipe.*,
	cat.Name as CategoryDisplay,
	author.UserName as Author,
	createUser.UserName as CreateUserDisplay,
	updateUser.UserName as UpdateUserDisplay
from Recipes recipe
left join Users as createUser on recipe.CreateUser = createUser.Id
left join Users as updateUser on recipe.UpdateUser = updateUser.Id
left join Users as author on recipe.AuthorId = author.Id
left join Category as cat on recipe.CategoryId = cat.Id
where
	(@keyword = null or @keyword = '' or (recipe.Name like N'%' + @keyword + '%' or recipe.Origin like N'%' + @keyword + '%' or recipe.CookTime like N'%' + @keyword + '%'))
	and (@catId = 0 or recipe.CategoryId = @catId)
	and (@authorId = 0 or recipe.AuthorId = @authorId)
	and (@name = null or @name = '' or recipe.Name like N'%' + @name + '%')
	and (@origin = null or @origin = '' or recipe.Origin like N'%' + @origin + '%')
	and recipe.Serves >= @minServer
	and (@maxServer <= 0 or recipe.Serves <= @maxServer)
	and recipe.TotalViews >= @minTotalViews
	and (@maxTotalViews <= 0 or recipe.TotalViews <= @maxTotalViews)
	and (@cookTime = null or @cookTime = '' or recipe.CookTime like N'%' + @cookTime + '%')
	and (@status = -1 or recipe.Status = @status)
go

create proc GetRecipeById
	@id int
as
select 
	recipe.*,
	cat.Name as CategoryDisplay,
	author.UserName as Author,
	createUser.UserName as CreateUserDisplay,
	updateUser.UserName as UpdateUserDisplay
from Recipes recipe
left join Users as createUser on recipe.CreateUser = createUser.Id
left join Users as updateUser on recipe.UpdateUser = updateUser.Id
left join Users as author on recipe.AuthorId = author.Id
left join Category as cat on recipe.CategoryId = cat.Id
left join Rating as rat on recipe.Id = rat.RecipeId
where recipe.Id = @id
go

create proc GetRecipeIdAfterInsert
	@catId int,
	@authorId int,
	@name nvarchar(250)
as
begin
	select top 1 * from Recipes
	where 
		CategoryId = @catId
		and AuthorId = @authorId
		and Name like N'%' + @name + '%'
		and TotalViews = 1
		and Status = 0
	order by Id desc
end
go

--proc step

create proc FilterListSteps
	@recipeId int
as
begin
	select 
		step.*
	from Steps step
	where step.RecipeId = @recipeId and step.Status = 0
	order by step.StepNumber
end
go

create proc GetStepById
	@id int
as
	select 
		step.*
	from Steps step
	where step.Id = @id
go

-- prop FoodIngredient
create proc getIngredient
	@recipeId int
as
select 
	fi.*
from Ingredient fi
where fi.Status = 0 and fi.RecipeId = @recipeId
go

select * from Ingredient
go

create proc GetIngredientById
	@id int
as
	select 
		*
	from Ingredient
	where Id = @id
go

-- proc Users
create proc GetAllUsers
as
select 
	u.*,
	createUser.UserName as CreateUserDisplay,
	updateUser.UserName as UpdateUserDisplay
from Users as u
left join Users as createUser on u.CreateUser = createUser.Id
left join Users as updateUser on u.UpdateUser = updateUser.Id
order by id desc
go

create proc FilterListUsers
	@keyword nvarchar(250),
	@sex int,
	@role int,
	@status int
as
select 
	u.*,
	createUser.UserName as CreateUserDisplay,
	updateUser.UserName as UpdateUserDisplay
from Users as u
left join Users as createUser on u.CreateUser = createUser.Id
left join Users as updateUser on u.UpdateUser = updateUser.Id
where (u.UserName like N'%' + @keyword + '%'
		or u.DisplayName like N'%' + @keyword + '%'
		or u.Address like N'%' + @keyword + '%'
		or u.PhoneNumber like N'%' + @keyword + '%'
		or u.Email like N'%' + @keyword + '%'
	  )
	  and (@sex = - 1 or u.Sex = @sex)
	  and (@role = - 1 or u.Role = @role)
	  and (@status = -1 or u.Status = @status)
order by id desc
go

create proc GetUserById
	@id int
as
select 
	u.*,
	createUser.UserName as CreateUserDisplay,
	updateUser.UserName as UpdateUserDisplay
from Users as u
left join Users as createUser on u.CreateUser = createUser.Id
left join Users as updateUser on u.UpdateUser = updateUser.Id
where u.Id = @id
go

create proc LoginUser
	@userName varchar(250),
	@password varchar(250)
as
select 
	u.*,
	createUser.UserName as CreateUserDisplay,
	updateUser.UserName as UpdateUserDisplay
from Users as u
left join Users as createUser on u.CreateUser = createUser.Id
left join Users as updateUser on u.UpdateUser = updateUser.Id
where (u.UserName = @userName 
		or u.PhoneNumber = @userName
		or u.Email = @userName
	)
	and u.Password = @password
go

--proc logindevice
create proc GetDetailLoginDevice
	@userId int,
	@deviceName varchar(250)
as
	select * from LoginDevice
where UserId = @userId
	and DeviceName = @deviceName
go


-- proc rating
create proc GetRatingByRecipeId
	@recipeId int
as
	select 
		*
	from Rating
	where RecipeId = @recipeId
go

create proc GetReceptionRating
as
select 
	recipe.Id as Id,
	AVG(rating.Rating) as AvgRating,
	COUNT(rating.Id) as TotalRating
from Recipes recipe
left join Rating rating on recipe.Id = rating.RecipeId
group by recipe.Id
go

-- proc follower
create proc GetListFollowOtherUser
	@userId int
as
select 
	followedByOther.*,
	createUser.UserName as CreateUserDisplay,
	updateUser.UserName as UpdateUserDisplay
from Followers as fo
left join Users as followOther on followOther.Id = fo.UserId
left join Users as followedByOther on followedByOther.Id = fo.FollowerId
left join Users as createUser on followOther.CreateUser = createUser.Id
left join Users as updateUser on followOther.UpdateUser = updateUser.Id
where followOther.Status = 0 and  followOther.Id = @userId
go

create proc GetListFollowedByOthersUser
	@followerId int
as
select 
	followOther.*,
	createUser.UserName as CreateUserDisplay,
	updateUser.UserName as UpdateUserDisplay
from Followers as fo
left join Users as followOther on followOther.Id = fo.UserId
left join Users as followedByOther on followedByOther.Id = fo.FollowerId
left join Users as createUser on followOther.CreateUser = createUser.Id
left join Users as updateUser on followOther.UpdateUser = updateUser.Id
where followOther.Status = 0 and  followedByOther.Id = @followerId
go

exec GetAllUsers
go
exec FilterListUsers N'',1,1,-1
go
exec FilterListRecipes N'',0,0,N'',N'',0,0,0,0,N'',-1
go

insert into Rating values 
	(1, 1, 3,''),
	(1, 1, 4,''),
	(1, 1, 5,''),
	(1, 1, 3,'')
go

insert into RecipesSave values(1,1,0,'2022-10-30')
go