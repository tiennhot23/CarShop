create database CAR

use CAR

create table AD(
	username varchar(50) primary key,
	pass varchar(50) not null
)

create table Classs(
	name nvarchar(50) primary key,
	disc nvarchar(max) not null,
	img varchar(200) not null,
)

create table Brands(
	name nvarchar(50) primary key,
	disc nvarchar(max) not null,
	img varchar(200) not null,
)

create table Cars(
	id int primary key identity(1,1),
	img varchar(200) not null,
	name nvarchar(50) not null unique,
	video varchar(200),
	amount int default 0,
	class nvarchar(50) not null foreign key references Classs(name),
	disc nvarchar(max) not null,
	price numeric not null,
	brand nvarchar(50) not null foreign key references Brands(name)
)

create table Orders(
	id int primary key identity(1,1),
	oid AS 'OID' + RIGHT('0000000000' + CAST(ID AS VARCHAR(10)), 10) PERSISTED,
	email varchar(100) not null,
	customer nvarchar(100) not null,
	carId int not null foreign key references Cars(id),
	phone nvarchar(15) not null,
	addres nvarchar(100) not null,
	datebuy date not null,
	amount int not null,
	total numeric not null,
	stat int not null,
)

create table Securities(
	token varchar(100) primary key,
	expired varchar(15) not null,
	orderId int not null foreign key references Orders(id),
)

insert into AD values ('sa', '123')

insert into Classs values
('Sport', N'https://vi.wikipedia.org/wiki/Xe_th%E1%BB%83_thao', N'https://i.pinimg.com/originals/24/15/55/241555e86887d2bde3dd100f90d71746.jpg'),
('Super', N'https://vi.wikipedia.org/wiki/Si%C3%AAu_xe', N'https://w0.peakpx.com/wallpaper/417/369/HD-wallpaper-lotus-evija-2020-rear-view-hypercar-new-silver-evija-supercar-british-sports-cars-lotus.jpg'),
('Race', N'https://en.wikipedia.org/wiki/Auto_racing', N'https://w0.peakpx.com/wallpaper/731/912/HD-wallpaper-bmw-bmw-z4-gt3-car-coupe-race-car-sport-car-white-car.jpg'),
('Concept', N'https://en.wikipedia.org/wiki/Concept_car', N'https://w0.peakpx.com/wallpaper/501/450/HD-wallpaper-2021-rolls-royce-ghost-front-view-exterior-luxury-white-sedan-new-white-ghost-british-cars-rolls-royce.jpg'),
('Muscle', N'https://en.wikipedia.org/wiki/Muscle_car', N'https://thumbs.dreamstime.com/b/classic-black-car-studio-shot-isolated-white-background-66971386.jpg'),
('Luxury', N'https://vi.wikipedia.org/wiki/Xe_sang_tr%E1%BB%8Dng', N'https://i.pinimg.com/originals/03/71/d2/0371d245a9218e1929fb479325a799b8.jpg'),
('Offroad', N'https://en.wikipedia.org/wiki/Off-road_vehicle', N'https://thumbs.dreamstime.com/b/white-off-road-four-wheel-drive-car-side-view-isolated-white-background-white-off-road-four-wheel-drive-car-side-view-116213337.jpg');

insert into Brands values
('BMW', N'https://vi.wikipedia.org/wiki/BMW', N'https://giaxetot.vn/uploads/logo-bmw.png'),
('Ferrari', N'https://vi.wikipedia.org/wiki/Ferrari', N'https://giaxetot.vn/uploads/logo-ferrari.png'),
('Ford', N'https://vi.wikipedia.org/wiki/Ford', N'https://giaxetot.vn/uploads/logo-ford.png'),
('Porsche', N'https://vi.wikipedia.org/wiki/Porsche', N'https://giaxetot.vn/uploads/logo-porsche.png'),
('Lamborghini', N'https://vi.wikipedia.org/wiki/Lamborghini', N'https://giaxetot.vn/uploads/logo-lamborghini.png'),
('Audi', N'https://vi.wikipedia.org/wiki/Audi', N'https://giaxetot.vn/uploads/Audi.png');

insert into Cars values
(N'https://danchoioto.vn/wp-content/uploads/2020/10/gia-bmw-330i.jpg', 'BMW 330i M', '', 50, 'Sport', N'Xe BMW 330i M Sport có tất cả các đặc điểm thông thường của dòng 3 Series, nhưng được trang bị bộ bodykit mạnh mẽ hơn, thể thao hơn, bánh xe hợp kim lớn hơn và nổi bật hơn cả là viền ngoại thất bóng loáng cùng một ít huy hiệu “M Sport” xung quanh cabin', 2400000000, 'BMW'),
(N'https://media.ed.edmunds-media.com/bmw/i8/2019/oem/2019_bmw_i8_coupe_base_fq_oem_1_1600.jpg', 'BMW i8', '', 50, 'Super', N'Được ra mắt vào hồi tháng 6/2014, siêu xe thể thao hybrid BMW i8 mang kiểu dáng cực đẹp đã nhanh chóng “đốn tim“ giới đam mê tốc độ. Dù được phân phối chính thức tại hơn 50 thị trường nhưng i8 BMW hiện tại lại không có Việt Nam.', 3800000000, 'BMW'),
(N'https://cly.1cdn.vn/2020/11/20/lamborghini-huracan-sto_2.jpg', 'Huracan', '', 50, 'Sport', N'Thiết kế của Lamborghini Huracan mang đến nhiều xúc cảm về sự mạnh mẽ và nguồn năng lượng bên trong. Vẫn dáng hình đặc trưng của “nhà siêu bò” nhưng hãng xe ô tô Ý đã thổi hồn của Lamborghini Huracan một chất rất riêng.', 4700000000, 'Lamborghini'),
(N'https://i1-vnexpress.vnecdn.net/2020/12/17/lamborghini-sc20-squadra-corse-9205-9699-1608198430.jpg?w=300&h=180&q=100&dpr=1&fit=crop&s=Jx-f_oV3xo3bU6sUwb29wA', 'Lamborghini SC20', '', 50, 'Super', N'Siêu xe không mui phát triển dựa trên Aventador SVJ và trang bị động cơ 6.5 V12 hút khí tự nhiên, công suất 759 mã lực, mô-men xoắn cực đại 720 Nm. Hộp số tự động 7 cấp kết hợp dẫn động bốn bánh.', 13400000000, 'Lamborghini'),
(N'https://cdn.motor1.com/images/mgl/168v9/s4/audi-rs3-lms-tcr-race-car.webp', 'Audi RS3', '', 50, 'Race', N'Audi RS3 2022 có diện mạo cá tính, đậm chất thể thao với mặt ca-lăng sơn màu đen kết hợp với các khe hút gió lớn hơn và lưới tản nhiệt dạng lưới tổ ong mới. Bên cạnh đó, xe cũng được trang bị cụm đèn pha LED bắt mắt hơn, chắn bùn phía trước rộng hơn, và bổ sung các khe hút gió ở vòm bánh trước. Ở phía sau, đuôi xe RS3 mới sở hữu cửa có kích thước lớn hơn, đèn hậu được tinh chỉnh, bộ khuếch tán sau chứa hệ thống ống cả RS Sport có âm thanh uy lực hơn đời cũ.', 1600000000, 'Audi'),
(N'https://i.pinimg.com/originals/df/d7/29/dfd7290194c552ceb0366bd7402aa71c.jpg', 'Audi R8', '', 50, 'Luxury', N'Audi R8 luôn là mẫu xe thể thao được ưa chuộng trên toàn thế giới kể từ khi trình làng. Đây cũng là mẫu xe từng sát cánh với Iron Man trong nhiều sản phẩm phim bom tấn siêu anh hùng của Marvel. Tính tới năm 2016, doanh số Audi R8 trên toàn cầu đạt gần 30.000 chiếc.', 9000000000, 'Audi'),
(N'https://znews-photo.zadn.vn/w660/Uploaded/obunzvu/2019_08_28/porsche988visionconceptrendering.jpg', 'Porsche 988 Vision', '', 50, 'Concept', N'Porsche 988 Vision là mẫu siêu xe tiếp theo của Porsche, dự định ra mắt năm 2025. Cùng chiêm ngưỡng thiết kế concept mới nhất của siêu xe này. Những chiếc Porsche 918 Spyder cuối cùng đã được lắp ráp vào giữa năm 2015, kết thúc vòng đời mẫu hypercar hàng đầu của hãng xe Đức.', 8300000000, 'Porsche'),
(N'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTRc4hM8gjDIFmPKvLyG91dqa9ngPf-a6kMMQ&usqp=CAU', 'Porsche 981 Spyder', '', 50, 'Super', N'The Porsche 981 is the internal designation given to the third-generation of the Boxster and second generation of the Cayman models built by German automobile manufacturer Porsche. It was announced on 13 March 2012 at the Geneva Auto Show with sales starting early summer 2012.', 7100000000, 'Porsche'),
(N'https://xe360.com/uploads/forum/photo/us/us2/us2ox64b715b.webp', 'Ford Mustang', '', 50, 'Sport', N'Ford Mustang là dòng xe thể thao mạnh mẽ có xuất xứ từ Mỹ, được ra mắt năm 1964. Trải qua nhiều thế hệ, Mustang trở thành xe thể thao ưa thích trên toàn thế giới bởi kiểu dáng đẹp mắt, động cơ vận hành mạnh mẽ và nhiều ưu điểm nổi bật khác.', 2900000000, 'Ford'),
(N'https://media.ford.com/content/fordmedia/fna/us/en/news/2019/07/04/limited-edition-ford-gt/jcr:content/image.img.881.495.jpg/1562230551502.jpg', 'Ford GT', '', 50, 'Race', N'Ford GT 2017 là thế hệ thứ hai của dòng xe thể thao thương mại danh tiếng thuộc Ford, bắt đầu sản xuất từ cuối 2016 và dự kiến kéo dài đến 2022. Xe được lắp ráp tại nhà máy Multimatic, thành phố Markham, bang Ontario, Canada', 8600000000, 'Ford'),
(N'https://i1-vnexpress.vnecdn.net/2021/06/25/Bai-Ferrari-296-GTB-1-1-1787-1624610298.jpg?w=680&h=0&q=100&dpr=1&fit=crop&s=poDZ2_mqNcEewEI_wEcOvg', 'Ferrari 296 GTB', '', 50, 'Luxury', N'Thương hiệu xe thể thao Italy giới thiệu mẫu siêu xe mới mang tên 296 GTB, bổ sung thêm lựa chọn cho khách hàng yêu thích dòng xe Ferrari. Điểm nhấn của 296 GTB nằm ở hệ truyền động hybrid, định vị nằm dưới F8 Tributo và là đối thủ của McLaren Artura.', 8600000000, 'Ferrari')

insert into Orders values
('mark@gmail.com',	'mark',		1,	'1234567890',	N'596/37 Trần Hưng Đạo',	'2019-06-10',	2,		1000000000,	2),
('ana@gmail.com',	'ana',		2,	'1234567890',	N'596/37 Trần Hưng Đạo',	'2019-10-10',	1,		900000000,	-1),
('simp@gmail.com',	'simp',		2,	'1234567890',	N'596/37 Trần Hưng Đạo',	'2020-08-10',	2,		800000000,	-1),
('son@gmail.com',	'son',		5,	'1234567890',	N'596/37 Trần Hưng Đạo',	'2021-03-10',	1,		9100000000,	-1),
('nav@gmail.com',	'nav',		7,	'1234567890',	N'596/37 Trần Hưng Đạo',	'2021-10-10',	1,		2300000000,	1),
('david@gmail.com',	'david',	3,	'1234567890',	N'596/37 Trần Hưng Đạo',	'2021-10-10',	3,		2000000000,	-2)