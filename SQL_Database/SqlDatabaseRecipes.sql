﻿Create database DatabaseRecipes 
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
	Avatar ntext,
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
	Image ntext,
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
	Calories float,
	Fat float,
	Protein float,
	Carbo float,
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
	Status int default 0,
	CreateDate date not null default getdate(),
	CreateUser int not null default 0,
	UpdateDate date,
	UpdateUser int
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
(N'Đồ Việt Nam','', 0, '2022-10-23', 1, null, null),
(N'Đồ Trung Quốc','', 1, '2022-10-23', 1, null, null)
go

insert into Users values (N'Anh.vu3', N'Vũ Quỳnh Anh', 1, N'Yên Bái', '0787424822', 'jjadaskjdasfafkengewgwgwgsagsg', 'anh.vu3@sotatek.com', 'DEV C#', 1, 'image', N'Người phát triển API', 0, '2022/10/24', 0, '', 0)
go
insert into Users values (N'Duyen.pham', N'Phạm Thị Duyên', 0, N'Thái Bình', '0787114822', 'sdgsadgsdg', 'duyen.pham@sotatek.com', 'DEV C#', 1, 'image', N'Người phát triển API', 0, '2022/10/28', 0, '', 0)
go
insert into Users values (N'My.nguyen', N'Nguyễn Thanh Mỹ', 1, N'Hà Tây', '0787115522', 'asdg', 'my.nguyen@sotatek.com', 'DEV C#', 0, 'image', N'Người phát triển API', 0, '2022/10/28', 1, '', 0)
go
insert into Users values (N'Long.Tran', N'Trần Quang Long', 1, N'Nam Định', '0187115522', 'htrkgjvrtjnkrvtnj', 'long.tran@sotatek.com', 'DEV C#', 0, 'image', N'Người phát triển API', 0, '2022/10/28', 1, '', 0)
go
select * from Recipes
insert into Recipes values(1,1,'Rice','Viet Nam', 3, 3.5, 2.1, 12, 12, '',1,'',0,'2022-10-25',1,null,null)
go
insert into Recipes values(2,1,'Fist','Thai Lan', 7, 3.5, 1.1, 11, 12, '',100,'',1,'2022-10-25',1,null,null)
go

insert into Steps values (1, 1, 'Cook com', 0)
go

-- Proc Category
create proc GetAllCategory
as
select 
		cat.Id,
		cat.Name,
		cast(cat.Image as nvarchar(max)) as Image,
		cat.Status,
		cat.CreateDate,
		cat.CreateUser,
		cat.UpdateDate,
		cat.UpdateUser,
		COUNT(recipe.Id) as TotalRecipes,
		createUser.UserName as CreateUserDisplay,
		updateUser.UserName as UpdateUserDisplay
	from Category cat
	left join Users as createUser on cat.CreateUser = createUser.Id
	left join Users as updateUser on cat.UpdateUser = updateUser.Id
	left join Recipes as recipe on cat.Id = recipe.CategoryId
	group by 
		cat.Id,
		cat.Name,
		cast(cat.Image as nvarchar(max)),
		cat.Status,
		cat.CreateDate,
		cat.CreateUser,
		cat.UpdateDate,
		cat.UpdateUser,
		createUser.UserName,
		updateUser.UserName
go

create proc FilterListCategory
	@keyword nvarchar(250),
	@isGetAll bit,
	@sortIdDESC bit,
	@sortNameASC bit,
	@sortTotalRecipeDESC bit,
	@pageIndex int,
	@pageSize int
as
begin
	select 
		cat.Id,
		cat.Name,
		cast(cat.Image as nvarchar(max)) as Image,
		cat.Status,
		cat.CreateDate,
		cat.CreateUser,
		cat.UpdateDate,
		cat.UpdateUser,
		COUNT(recipe.Id) as TotalRecipes,
		createUser.UserName as CreateUserDisplay,
		updateUser.UserName as UpdateUserDisplay
	from Category cat
	left join Users as createUser on cat.CreateUser = createUser.Id
	left join Users as updateUser on cat.UpdateUser = updateUser.Id
	left join Recipes as recipe on cat.Id = recipe.CategoryId
	where
		cat.Name like N'%' + @keyword + '%'
		and (@isGetAll = 1 or cat.Status = 0)
	group by 
		cat.Id,
		cat.Name,
		cast(cat.Image as nvarchar(max)),
		cat.Status,
		cat.CreateDate,
		cat.CreateUser,
		cat.UpdateDate,
		cat.UpdateUser,
		createUser.UserName,
		updateUser.UserName
	order by 
		case @sortTotalRecipeDESC when 1 then COUNT(recipe.Id) end desc,
		case @sortIdDESC when 1 then cat.id end desc,
		case @sortNameASC when 1 then cat.Name end asc
	OFFSET ((@pageIndex - 1) * @pageSize) Rows  
	Fetch NEXT @pageSize ROWS ONLY  
end
go

create proc GetCategoryById
	@id int
as
	select 
		cat.Id,
		cat.Name,
		cast(cat.Image as nvarchar(max)) as Image,
		cat.Status,
		cat.CreateDate,
		cat.CreateUser,
		cat.UpdateDate,
		cat.UpdateUser,
		COUNT(recipe.Id) as TotalRecipes,
		createUser.UserName as CreateUserDisplay,
		updateUser.UserName as UpdateUserDisplay
	from Category cat
	left join Users as createUser on cat.CreateUser = createUser.Id
	left join Users as updateUser on cat.UpdateUser = updateUser.Id
	left join Recipes as recipe on cat.Id = recipe.CategoryId
	where cat.Id = @id
	group by 
		cat.Id,
		cat.Name,
		cast(cat.Image as nvarchar(max)),
		cat.Status,
		cat.CreateDate,
		cat.CreateUser,
		cat.UpdateDate,
		cat.UpdateUser,
		createUser.UserName,
		updateUser.UserName
go

-- Proc Recipes
	
create proc GetAllRecipes
as
select 
	recipe.Id,
	recipe.CategoryId,
	recipe.AuthorId,
	recipe.Name,
	recipe.Origin,
	recipe.Serves,
	cast(recipe.Image as nvarchar(max)) as Image,
	recipe.TotalViews,
	recipe.Calories,
	recipe.Carbo,
	recipe.Fat,
	recipe.Protein,
	recipe.CookTime,
	recipe.Status,
	recipe.CreateDate,
	recipe.CreateUser,
	recipe.UpdateDate,
	recipe.UpdateUser,
	Case 
		when AVG(rating.Rating) is null then 5
		else AVG(rating.Rating)
	end	as AvgRating,
	COUNT(rating.Id) as TotalRating,
	cat.Name as CategoryDisplay,
	author.UserName as Author,
	createUser.UserName as CreateUserDisplay,
	updateUser.UserName as UpdateUserDisplay
from Recipes recipe
left join Users as createUser on recipe.CreateUser = createUser.Id
left join Users as updateUser on recipe.UpdateUser = updateUser.Id
left join Users as author on recipe.AuthorId = author.Id
left join Category as cat on recipe.CategoryId = cat.Id
left join Rating rating on recipe.Id = rating.RecipeId
group by 
	recipe.Id,
	recipe.CategoryId,
	recipe.AuthorId,
	recipe.Name,
	recipe.Origin,
	recipe.Serves,
	cast(recipe.Image as nvarchar(max)),
	recipe.TotalViews,
	recipe.CookTime,
	recipe.Calories,
	recipe.Carbo,
	recipe.Fat,
	recipe.Protein,
	recipe.Status,
	recipe.CreateDate,
	recipe.CreateUser,
	recipe.UpdateDate,
	recipe.UpdateUser,
	cat.Name,
	author.UserName,
	createUser.UserName,
	updateUser.UserName
go

create proc GetSaveRecipes
	@userId int
as
select 
	recipe.Id,
	recipe.CategoryId,
	recipe.AuthorId,
	recipe.Name,
	recipe.Origin,
	recipe.Serves,
	cast(recipe.Image as nvarchar(max)) as Image,
	recipe.TotalViews,
	recipe.CookTime,
	recipe.Calories,
	recipe.Carbo,
	recipe.Fat,
	recipe.Protein,
	recipe.Status,
	recipe.CreateDate,
	recipe.CreateUser,
	recipe.UpdateDate,
	recipe.UpdateUser,
	Case 
		when AVG(rating.Rating) is null then 5
		else AVG(rating.Rating)
	end	as AvgRating,
	COUNT(rating.Id) as TotalRating,
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
left join Rating rating on recipe.Id = rating.RecipeId
where recipe.Status = 0 and recipeSave.UserId = @userId
group by 
	recipe.Id,
	recipe.CategoryId,
	recipe.AuthorId,
	recipe.Name,
	recipe.Origin,
	recipe.Calories,
	recipe.Carbo,
	recipe.Fat,
	recipe.Protein,
	recipe.Serves,
	cast(recipe.Image as nvarchar(max)),
	recipe.TotalViews,
	recipe.CookTime,
	recipe.Status,
	recipe.CreateDate,
	recipe.CreateUser,
	recipe.UpdateDate,
	recipe.UpdateUser,
	cat.Name,
	author.UserName,
	createUser.UserName,
	updateUser.UserName,
	recipeSave.CreateDate
go

create proc FilterListRecipes
	@keyword nvarchar(250),
	@catId int,
	@authorId int,
	@name nvarchar(250),
	@origin nvarchar(250),
	@ingredient nvarchar(250),
	@minServer int,
	@maxServer int,
	@minTotalViews int,
	@maxTotalViews int,
	@minTotalRating int,
	@maxTotalRating int,
	@minAvgRating int,
	@maxAvgRating int,
	@minCalories float,
	@maxCalories float,
	@minFat float,
	@maxFat float,
	@minProtein float,
	@maxProtein float,
	@minCarbo float,
	@maxCarbo float,
	@cookTime nvarchar(250),
	@status int,
	@sortByIdDESC bit,
	@sortByNameASC bit,
	@sortByServesASC bit,
	@sortByServesDESC bit,
	@sortByTotalViewDESC bit,
	@sortByAvgRatingDESC bit,
	@sortByTotalRatingDESC bit,
	@sortByCaloriesDESC bit,
	@sortByFatDESC bit,
	@sortByProteinDESC bit,
	@sortByCarbo bit,
	@pageIndex int,
	@pageSize int
as
select 
	recipe.Id,
	recipe.CategoryId,
	recipe.AuthorId,
	recipe.Name,
	recipe.Origin,
	recipe.Serves,
	cast(recipe.Image as nvarchar(max)) as Image,
	recipe.TotalViews,
	recipe.CookTime,
	recipe.Status,
	recipe.CreateDate,
	recipe.CreateUser,
	recipe.Calories,
	recipe.Carbo,
	recipe.Fat,
	recipe.Protein,
	recipe.UpdateDate,
	recipe.UpdateUser,
	Case 
		when AVG(rating.Rating) is null then 5
		else AVG(rating.Rating)
	end	as AvgRating,
	COUNT(rating.Id) as TotalRating,
	cat.Name as CategoryDisplay,
	author.UserName as Author,
	createUser.UserName as CreateUserDisplay,
	updateUser.UserName as UpdateUserDisplay
from Recipes recipe
left join Users as createUser on recipe.CreateUser = createUser.Id
left join Users as updateUser on recipe.UpdateUser = updateUser.Id
left join Users as author on recipe.AuthorId = author.Id
left join Category as cat on recipe.CategoryId = cat.Id
left join Rating rating on recipe.Id = rating.RecipeId
left join Ingredient ingredient on recipe.Id = ingredient.RecipeId
where
	(@keyword = null or @keyword = '' or (recipe.Name like N'%' + @keyword + '%' or recipe.Origin like N'%' + @keyword + '%' or recipe.CookTime like N'%' + @keyword + '%'))
	and (@catId = 0 or recipe.CategoryId = @catId)
	and (@authorId = 0 or recipe.AuthorId = @authorId)
	and (@name = null or @name = '' or recipe.Name like N'%' + @name + '%')
	and (@origin = null or @origin = '' or recipe.Origin like N'%' + @origin + '%')
	and (@ingredient = null or @ingredient = '' or ingredient.Name like N'%' + @ingredient + '%')
	and recipe.Serves >= @minServer
	and (@maxServer <= 0 or recipe.Serves <= @maxServer)
	and recipe.TotalViews >= @minTotalViews
	and (@maxTotalViews <= 0 or recipe.TotalViews <= @maxTotalViews)
	and recipe.Calories >= @minCalories
	and (@maxCalories <= 0 or recipe.Calories <= @maxCalories)
	and recipe.Fat >= @minFat
	and (@maxFat <= 0 or recipe.Fat <= @maxFat)
	and recipe.Protein >= @minProtein
	and (@maxProtein <= 0 or recipe.Protein <= @maxProtein)
	and recipe.Carbo >= @minCarbo
	and (@maxCarbo <= 0 or recipe.Carbo <= @maxCarbo)
	and (@cookTime = null or @cookTime = '' or recipe.CookTime like N'%' + @cookTime + '%')
	and (@status = -1 or recipe.Status = @status)
group by 
	recipe.Id,
	recipe.CategoryId,
	recipe.AuthorId,
	recipe.Name,
	recipe.Origin,
	recipe.Serves,
	cast(recipe.Image as nvarchar(max)),
	recipe.TotalViews,
	recipe.CookTime,
	recipe.Status,
	recipe.CreateDate,
	recipe.Calories,
	recipe.Carbo,
	recipe.Fat,
	recipe.Protein,
	recipe.CreateUser,
	recipe.UpdateDate,
	recipe.UpdateUser,
	cat.Name,
	author.UserName,
	createUser.UserName,
	updateUser.UserName
having 
	COUNT(rating.Id) >= @minTotalRating
	and (@maxTotalRating <= 0 or COUNT(rating.Id) <= @maxTotalRating)
	and (Case 
			when AVG(rating.Rating) is null then 5
			else AVG(rating.Rating)
		end) >= @minAvgRating
	and (@maxAvgRating <= 0 or 
		(Case 
			when AVG(rating.Rating) is null then 5
			else AVG(rating.Rating)
		end) <= @maxAvgRating)
order by 
	case @sortByIdDESC when 1 then recipe.id end desc,
	case @sortByNameASC when 1 then recipe.Name end asc,
	case @sortByServesASC when 1 then recipe.Serves end asc,
	case @sortByServesDESC when 1 then recipe.Serves end desc,
	case @sortByTotalViewDESC when 1 then recipe.TotalViews end desc,
	case @sortByCaloriesDESC when 1 then recipe.Calories end desc,
	case @sortByFatDESC when 1 then recipe.Fat end desc,
	case @sortByProteinDESC when 1 then recipe.Protein end desc,
	case @sortByCarbo when 1 then recipe.Carbo end desc,
	case @sortByAvgRatingDESC when 1 then 
		(Case 
			when AVG(rating.Rating) is null then 5
			else AVG(rating.Rating)
		end) end desc,
	case @sortByTotalRatingDESC when 1 then COUNT(rating.Id) end desc
OFFSET ((@pageIndex - 1) * @pageSize) Rows  
Fetch NEXT @pageSize ROWS ONLY  
go

create proc GetRecipeById
	@id int
as
select 
	recipe.Id,
	recipe.CategoryId,
	recipe.AuthorId,
	recipe.Name,
	recipe.Origin,
	recipe.Serves,
	cast(recipe.Image as nvarchar(max)) as Image,
	recipe.TotalViews,
	recipe.CookTime,
	recipe.Calories,
	recipe.Carbo,
	recipe.Fat,
	recipe.Protein,
	recipe.Status,
	recipe.CreateDate,
	recipe.CreateUser,
	recipe.UpdateDate,
	recipe.UpdateUser,
	(Case 
			when AVG(rating.Rating) is null then 5
			else AVG(rating.Rating)
	end) as AvgRating,
	COUNT(rating.Id) as TotalRating,
	cat.Name as CategoryDisplay,
	author.UserName as Author,
	createUser.UserName as CreateUserDisplay,
	updateUser.UserName as UpdateUserDisplay
from Recipes recipe
left join Users as createUser on recipe.CreateUser = createUser.Id
left join Users as updateUser on recipe.UpdateUser = updateUser.Id
left join Users as author on recipe.AuthorId = author.Id
left join Category as cat on recipe.CategoryId = cat.Id
left join Rating rating on recipe.Id = rating.RecipeId
where recipe.Id = @id
group by 
	recipe.Id,
	recipe.CategoryId,
	recipe.AuthorId,
	recipe.Calories,
	recipe.Carbo,
	recipe.Fat,
	recipe.Protein,
	recipe.Name,
	recipe.Origin,
	recipe.Serves,
	cast(recipe.Image as nvarchar(max)),
	recipe.TotalViews,
	recipe.CookTime,
	recipe.Status,
	recipe.CreateDate,
	recipe.CreateUser,
	recipe.UpdateDate,
	recipe.UpdateUser,
	cat.Name,
	author.UserName,
	createUser.UserName,
	updateUser.UserName
go

create proc GetRecipeIdAfterInsert
	@catId int,
	@authorId int,
	@name nvarchar(250),
	@serves int,
	@calories float,
	@fat float,
	@protein float,
	@carbo float
as
begin
	select top 1 * from Recipes
	where 
		CategoryId = @catId
		and AuthorId = @authorId
		and Name like N'%' + @name + '%'
		and Serves = @serves
		and Calories = @calories
		and Fat = @fat
		and Protein = @protein
		and Carbo = @carbo
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

-- prop Ingredient
create proc getIngredient
	@recipeId int
as
select 
	fi.*
from Ingredient fi
where fi.Status = 0 and fi.RecipeId = @recipeId
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
	u.Id,
	u.UserName,
	u.DisplayName,
	u.Sex,
	u.Address,
	u.PhoneNumber,
	u.Email,
	u.Job,
	u.Role,
	cast(u.Avatar as nvarchar(max)) as Avatar,
	cast(u.Description as nvarchar(max)) as Description,
	u.Status,
	u.CreateDate,
	u.CreateUser,
	u.UpdateDate,
	u.UpdateUser,
	createUser.UserName as CreateUserDisplay,
	updateUser.UserName as UpdateUserDisplay,
	COUNT(recipe.Id) as TotalRecipe,
	Sum(recipe.TotalViews) as TotalViews,
	COUNT(followOtherUser.UserId) as TotalFollowOtherUser,
	COUNT(followedByOthersUser.FollowerId) as TotalFollowedByOthersUser
from Users as u
left join Users as createUser on u.CreateUser = createUser.Id
left join Users as updateUser on u.UpdateUser = updateUser.Id
left join (select * from Recipes where Status = 0) as recipe on u.Id = recipe.AuthorId
left join (select * from Followers where Status = 0) as followOtherUser on u.Id = followOtherUser.UserId
left join (select * from Followers where Status = 0) as followedByOthersUser on u.Id = followedByOthersUser.FollowerId
group by 
	u.Id,
	u.UserName,
	u.DisplayName,
	u.Sex,
	u.Address,
	u.PhoneNumber,
	u.Email,
	u.Job,
	u.Role,
	cast(u.Avatar as nvarchar(max)),
	cast(u.Description as nvarchar(max)),
	u.Status,
	u.CreateDate,
	u.CreateUser,
	u.UpdateDate,
	u.UpdateUser,
	createUser.UserName,
	updateUser.UserName
order by id desc
go

create proc FilterListUsers
	@keyword nvarchar(250),
	@email varchar(250),
	@phoneNumber varchar(25),
	@displayName nvarchar(250),
	@userName varchar(250),
	@sex int,
	@role int,
	@status int,
	@sortByIdDESC bit,
	@sortByTotalRecipeDESC bit,
	@sortByTotalFollowOtherUserDESC bit,
	@sortByTotalFollowedByOthersUserDESC bit,
	@sortByTotalViewsDESC bit,
	@pageIndex int,
	@pageSize int
as
select 
	u.Id,
	u.UserName,
	u.DisplayName,
	u.Sex,
	u.Address,
	u.PhoneNumber,
	u.Email,
	u.Job,
	u.Role,
	cast(u.Avatar as nvarchar(max)) as Avatar,
	cast(u.Description as nvarchar(max)) as Description,
	u.Status,
	u.CreateDate,
	u.CreateUser,
	u.UpdateDate,
	u.UpdateUser,
	createUser.UserName as CreateUserDisplay,
	updateUser.UserName as UpdateUserDisplay,
	COUNT(recipe.Id) as TotalRecipe,
	Sum(recipe.TotalViews) as TotalViews,
	COUNT(followOtherUser.UserId) as TotalFollowOtherUser,
	COUNT(followedByOthersUser.FollowerId) as TotalFollowedByOthersUser
from Users as u
left join Users as createUser on u.CreateUser = createUser.Id
left join Users as updateUser on u.UpdateUser = updateUser.Id
left join (select * from Recipes where Status = 0) as recipe on u.Id = recipe.AuthorId
left join (select * from Followers where Status = 0) as followOtherUser on u.Id = followOtherUser.UserId
left join (select * from Followers where Status = 0) as followedByOthersUser on u.Id = followedByOthersUser.FollowerId
where (u.UserName like N'%' + @keyword + '%'
		or u.DisplayName like N'%' + @keyword + '%'
		or u.Address like N'%' + @keyword + '%'
		or u.PhoneNumber like N'%' + @keyword + '%'
		or u.Email like N'%' + @keyword + '%'
	  )
	  and (@phoneNumber = '' or u.PhoneNumber like N'%' +  @phoneNumber + '%')
	  and (@email = '' or u.Email like N'%' +  @email + '%')
	  and (@displayName = '' or u.Email like N'%' + @displayName + '%')
	  and (@userName = '' or u.UserName like N'%' + @userName + '%')
	  and (@sex = - 1 or u.Sex = @sex)
	  and (@role = - 1 or u.Role = @role)
	  and (@status = -1 or u.Status = @status)
group by 
	u.Id,
	u.UserName,
	u.DisplayName,
	u.Sex,
	u.Address,
	u.PhoneNumber,
	u.Email,
	u.Job,
	u.Role,
	cast(u.Avatar as nvarchar(max)),
	cast(u.Description as nvarchar(max)),
	u.Status,
	u.CreateDate,
	u.CreateUser,
	u.UpdateDate,
	u.UpdateUser,
	createUser.UserName,
	updateUser.UserName
order by 
	case @sortByTotalRecipeDESC when 1 then COUNT(recipe.Id) end desc,
	case @sortByTotalFollowOtherUserDESC when 1 then COUNT(followOtherUser.UserId) end desc,
	case @sortByTotalViewsDESC when 1 then Sum(recipe.TotalViews) end desc,
	case @sortByTotalFollowedByOthersUserDESC when 1 then COUNT(followedByOthersUser.FollowerId) end desc,
	case @sortByIdDESC when 1 then u.Id end desc
OFFSET ((@pageIndex - 1) * @pageSize) Rows  
Fetch NEXT @pageSize ROWS ONLY  
go

create proc GetUserById
	@id int
as
select 
	u.Id,
	u.UserName,
	u.DisplayName,
	u.Sex,
	u.Address,
	u.PhoneNumber,
	u.Email,
	u.Job,
	u.Role,
	cast(u.Avatar as nvarchar(max)) as Avatar,
	cast(u.Description as nvarchar(max)) as Description,
	u.Status,
	u.CreateDate,
	u.CreateUser,
	u.UpdateDate,
	u.UpdateUser,
	createUser.UserName as CreateUserDisplay,
	updateUser.UserName as UpdateUserDisplay,
	COUNT(recipe.Id) as TotalRecipe,
	Sum(recipe.TotalViews) as TotalViews,
	COUNT(followOtherUser.UserId) as TotalFollowOtherUser,
	COUNT(followedByOthersUser.FollowerId) as TotalFollowedByOthersUser
from Users as u
left join Users as createUser on u.CreateUser = createUser.Id
left join Users as updateUser on u.UpdateUser = updateUser.Id
left join (select * from Recipes where Status = 0) as recipe on u.Id = recipe.AuthorId
left join (select * from Followers where Status = 0) as followOtherUser on u.Id = followOtherUser.UserId
left join (select * from Followers where Status = 0) as followedByOthersUser on u.Id = followedByOthersUser.FollowerId
where u.Id = @id
group by 
	u.Id,
	u.UserName,
	u.DisplayName,
	u.Sex,
	u.Address,
	u.PhoneNumber,
	u.Email,
	u.Job,
	u.Role,
	cast(u.Avatar as nvarchar(max)),
	cast(u.Description as nvarchar(max)),
	u.Status,
	u.CreateDate,
	u.CreateUser,
	u.UpdateDate,
	u.UpdateUser,
	createUser.UserName,
	updateUser.UserName
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
	followedByOther.Id,
	followedByOther.UserName,
	followedByOther.DisplayName,
	followedByOther.Sex,
	followedByOther.Address,
	followedByOther.PhoneNumber,
	followedByOther.Email,
	followedByOther.Job,
	followedByOther.Role,
	cast(followedByOther.Avatar as nvarchar(max)) as Avatar,
	cast(followedByOther.Description as nvarchar(max)) as Description,
	followedByOther.Status,
	followedByOther.CreateDate,
	followedByOther.CreateUser,
	followedByOther.UpdateDate,
	followedByOther.UpdateUser,
	createUser.UserName as CreateUserDisplay,
	updateUser.UserName as UpdateUserDisplay,
	COUNT(recipe.Id) as TotalRecipe,
	Sum(recipe.TotalViews) as TotalViews,
	COUNT(followOtherUser.UserId) as TotalFollowOtherUser,
	COUNT(followedByOthersUser.FollowerId) as TotalFollowedByOthersUser
from Followers as fo
left join Users as followOther on followOther.Id = fo.UserId
left join Users as followedByOther on followedByOther.Id = fo.FollowerId
left join Users as createUser on followOther.CreateUser = createUser.Id
left join Users as updateUser on followOther.UpdateUser = updateUser.Id
left join (select * from Recipes where Status = 0) as recipe on followedByOther.Id = recipe.AuthorId
left join (select * from Followers where Status = 0) as followOtherUser on followedByOther.Id = followOtherUser.UserId
left join (select * from Followers where Status = 0) as followedByOthersUser on followedByOther.Id = followedByOthersUser.FollowerId
where followOther.Status = 0 and  followOther.Id = @userId
group by 
	followedByOther.Id,
	followedByOther.UserName,
	followedByOther.DisplayName,
	followedByOther.Sex,
	followedByOther.Address,
	followedByOther.PhoneNumber,
	followedByOther.Email,
	followedByOther.Job,
	followedByOther.Role,
	cast(followedByOther.Avatar as nvarchar(max)),
	cast(followedByOther.Description as nvarchar(max)),
	followedByOther.Status,
	followedByOther.CreateDate,
	followedByOther.CreateUser,
	followedByOther.UpdateDate,
	followedByOther.UpdateUser,
	createUser.UserName,
	updateUser.UserName
go

create proc GetListFollowedByOthersUser
	@followerId int
as
select 
	followOther.Id,
	followOther.UserName,
	followOther.DisplayName,
	followOther.Sex,
	followOther.Address,
	followOther.PhoneNumber,
	followOther.Email,
	followOther.Job,
	followOther.Role,
	cast(followOther.Avatar as nvarchar(max)) as Avatar,
	cast(followOther.Description as nvarchar(max)) as Description,
	followOther.Status,
	followOther.CreateDate,
	followOther.CreateUser,
	followOther.UpdateDate,
	followOther.UpdateUser,
	createUser.UserName as CreateUserDisplay,
	updateUser.UserName as UpdateUserDisplay,
	COUNT(recipe.Id) as TotalRecipe,
	Sum(recipe.TotalViews) as TotalViews,
	COUNT(followOtherUser.UserId) as TotalFollowOtherUser,
	COUNT(followedByOthersUser.FollowerId) as TotalFollowedByOthersUser
from Followers as fo
left join Users as followOther on followOther.Id = fo.UserId
left join Users as followedByOther on followedByOther.Id = fo.FollowerId
left join Users as createUser on followOther.CreateUser = createUser.Id
left join Users as updateUser on followOther.UpdateUser = updateUser.Id
left join (select * from Recipes where Status = 0) as recipe on followOther.Id = recipe.AuthorId
left join (select * from Followers where Status = 0) as followOtherUser on followOther.Id = followOtherUser.UserId
left join (select * from Followers where Status = 0) as followedByOthersUser on followOther.Id = followedByOthersUser.FollowerId
where followOther.Status = 0 and  followedByOther.Id = @followerId
group by
	followOther.Id,
	followOther.UserName,
	followOther.DisplayName,
	followOther.Sex,
	followOther.Address,
	followOther.PhoneNumber,
	followOther.Email,
	followOther.Job,
	followOther.Role,
	cast(followOther.Avatar as nvarchar(max)),
	cast(followOther.Description as nvarchar(max)),
	followOther.Status,
	followOther.CreateDate,
	followOther.CreateUser,
	followOther.UpdateDate,
	followOther.UpdateUser,
	createUser.UserName,
	updateUser.UserName
go

-- proc NotificationType
create proc GetAllNotificationType
as
select 
		notType.Id,
		notType.Name,
		notType.Status,
		notType.CreateDate,
		notType.CreateUser,
		notType.UpdateDate,
		notType.UpdateUser,
		createUser.UserName as CreateUserDisplay,
		updateUser.UserName as UpdateUserDisplay
	from NotificationType notType
	left join Users as createUser on notType.CreateUser = createUser.Id
	left join Users as updateUser on notType.UpdateUser = updateUser.Id
go

create proc FilterListNotificationType
	@keyword nvarchar(250),
	@isGetAll bit
as
select 
		notType.Id,
		notType.Name,
		notType.Status,
		notType.CreateDate,
		notType.CreateUser,
		notType.UpdateDate,
		notType.UpdateUser,
		createUser.UserName as CreateUserDisplay,
		updateUser.UserName as UpdateUserDisplay
	from NotificationType notType
	left join Users as createUser on notType.CreateUser = createUser.Id
	left join Users as updateUser on notType.UpdateUser = updateUser.Id
where
	notType.Name like N'%' + @keyword + '%'
	and (@isGetAll = 1 or notType.Status = 0)
go

create proc GetNotificationTypeById
	@id int
as
select 
		notType.Id,
		notType.Name,
		notType.Status,
		notType.CreateDate,
		notType.CreateUser,
		notType.UpdateDate,
		notType.UpdateUser,
		createUser.UserName as CreateUserDisplay,
		updateUser.UserName as UpdateUserDisplay
	from NotificationType notType
	left join Users as createUser on notType.CreateUser = createUser.Id
	left join Users as updateUser on notType.UpdateUser = updateUser.Id
where
	notType.Id = @id
go

-- proc Notification
create proc GetAllNotifications
as
select 
		notifi.*,
		createUser.UserName as CreateUserDisplay,
		forUser.UserName as UserDisplay
	from Notifications notifi
	left join Users as createUser on notifi.CreateUser = createUser.Id
	left join Users as forUser on notifi.UserId = forUser.Id
go

create proc FilterListNotificationByUserId
	@userId int,
	@status int
as
select 
		notifi.*,
		createUser.UserName as CreateUserDisplay,
		forUser.UserName as UserDisplay
	from Notifications notifi
	left join Users as createUser on notifi.CreateUser = createUser.Id
	left join Users as forUser on notifi.UserId = forUser.Id
	where 
		(notifi.UserId = @userId)and
		(@status = -1 or notifi.Status = @status)
go

create proc FilterListNotificationByCreateUserId
	@createUserId int,
	@status int
as
select 
		notifi.*,
		createUser.UserName as CreateUserDisplay,
		forUser.UserName as UserDisplay
	from Notifications notifi
	left join Users as createUser on notifi.CreateUser = createUser.Id
	left join Users as forUser on notifi.UserId = forUser.Id
	where 
		(notifi.CreateUser = @createUserId) and
		(@status = -1 or notifi.Status = @status)
go

create proc GetNotificationById
	@id int
as
select 
		notifi.*,
		createUser.UserName as CreateUserDisplay,
		forUser.UserName as UserDisplay
	from Notifications notifi
	left join Users as createUser on notifi.CreateUser = createUser.Id
	left join Users as forUser on notifi.UserId = forUser.Id
	where 
		notifi.Id = @id
go



select * from Notifications


select * from NotificationType
go
exec GetAllUsers
go
select * from Recipes
go

insert into Rating values 
	(1, 1, 3,''),
	(1, 1, 4,''),
	(1, 1, 5,''),
	(1, 1, 3,'')
go

insert into RecipesSave values(1,1,0,'2022-10-30')
go