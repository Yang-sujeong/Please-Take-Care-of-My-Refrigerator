SET foreign_key_checks = 0;
DROP TABLE member;
DROP TABLE shop;
DROP TABLE reservation;
DROP TABLE review;
DROP TABLE likelist;
SET foreign_key_checks = 1;

CREATE TABLE member(
	id	 	VARCHAR(20),
	pw 	 	VARCHAR(20),
	name	 	VARCHAR(20),
	birth		VARCHAR(20),
	phone  	 	VARCHAR(20),
	email 	 	VARCHAR(40)
);

CREATE TABLE shop(
	shop_num 	INT,
	shop_name	VARCHAR(20),
	shop_address	VARCHAR(100),
	shop_tel		VARCHAR(20),
	shop_type	VARCHAR(20),
	shop_time	VARCHAR(50),
	shop_hp		VARCHAR(100),
    shop_map	VARCHAR(20000)
);

CREATE TABLE reservation(
	rsv_num 		INT,
	id 	 	VARCHAR(20),
	shop_num 	INT,
	rsv_date 		DATE,
	rsv_totalnum	INT
);

CREATE TABLE review(
	rev_num 		INT,
	id		VARCHAR(20),
	shop_num 	INT,
	text 		VARCHAR(500),
	rating		VARCHAR(20)
);

CREATE TABLE likelist(
	likelist_num INT,
	id		VARCHAR(20),
	shop_num 	INT
);

ALTER TABLE member
ADD CONSTRAINT member_pk_id PRIMARY KEY(id);

ALTER TABLE shop
ADD CONSTRAINT shop_pk_num PRIMARY KEY(shop_num);

ALTER TABLE reservation
ADD CONSTRAINT rsv_pk_num PRIMARY KEY(rsv_num);

ALTER TABLE review
ADD CONSTRAINT rev_pk_num PRIMARY KEY(rev_num);

ALTER TABLE likelist
ADD CONSTRAINT member_pk_likelist_num PRIMARY KEY(likelist_num);

ALTER TABLE reservation
ADD CONSTRAINT member_id_fk FOREIGN KEY (id)  REFERENCES member(id);

-- ALTER TABLE reservation
-- ADD CONSTRAINT shop_num_fk FOREIGN KEY (shop_num)  REFERENCES shop(shop_num);

-- ALTER TABLE review
-- ADD CONSTRAINT member_id_fk2 FOREIGN KEY (id)  REFERENCES member(id);
 
ALTER TABLE review
ADD CONSTRAINT shop_num_fk2 FOREIGN KEY (shop_num)  REFERENCES shop(shop_num);

-- ALTER TABLE likelist
-- ADD CONSTRAINT shop_num_fk3 FOREIGN KEY (shop_num)  REFERENCES shop(shop_num);

-- 시퀀스 대신 사용, 자동으로 1씩 증가
ALTER TABLE reservation
   MODIFY COLUMN rsv_num INTEGER NOT NULL AUTO_INCREMENT;
ALTER TABLE review
   MODIFY COLUMN rev_num INTEGER NOT NULL AUTO_INCREMENT;
ALTER TABLE likelist
   MODIFY COLUMN likelist_num INTEGER NOT NULL AUTO_INCREMENT;
    
-- 아이디 유니크


INSERT INTO shop(shop_num, shop_name, shop_address, shop_tel, shop_type, shop_time, shop_hp, shop_map)
VALUES(	1,
	'마히나 비건 테이블',
	'서울특별시 강남구 논현로175길 75 2층',
	'070-4105-5331',
	'이탈리아음식',
	'12:00 ~ 21:30',
	'http://mahinavegan.com',
    'https://v4.map.naver.com/?searchCoord=26a025f526f7d2c9ce9cf69acbb4d74ac21691117c33dfc9f8340fd5a39911f6&query=66eI7Z6I64KYIOu5hOqxtCDthYzsnbTruJQ%3D&menu=location&tab=1&lng=ee131e9b0da8f9819cb6e590cbecc207&mapMode=0&mpx=499694cc644f8b95b846117ee5f212db593d1e27ec9838a63feeb51ba1995aae20b0ee5ee93011cea2270c88ec4097f4483822523a478eb9105f64ea320791ef&lat=9be38fd0e66797da7c5ca2f3ed114979&dlevel=12&enc=b64');

INSERT INTO shop(shop_num, shop_name, shop_address, shop_tel, shop_type, shop_time, shop_hp, shop_map)
VALUES(	2,
	'비건 베이커리 보물',
	'서울 강남구 논현로67길 11 1층',
	'02-558-0301',
	'베이커리',
	'12:00 ~ 20:00',
	'http://www.instagram.com/bo.mool_vegan',
    'https://v4.map.naver.com/?mpx=8357bae20d319edd8421b919abe16360b943e08dbfff77e043ac9428a48dde449b2fd612dc235a13a2d42acfae032dbc7f4b25133fceeadc6ae7c90fa82a9a4a&searchCoord=3096681b29d3aec95ad038b29518ea0014105a6d418845476286379dac8f701a&query=67mE6rG0IOuyoOydtOy7pOumrCDrs7TrrLw%3D&menu=location&tab=1&lng=986aa642a142dd80ebb24a8be3bd4e50&mapMode=0&lat=73288526c348c3ed57e28e69aeccd9e0&dlevel=12&enc=b64');

INSERT INTO shop(shop_num, shop_name, shop_address, shop_tel, shop_type, shop_time, shop_hp, shop_map)
VALUES(	3,
	'남미플랜트랩',
	'서울 서초구 방배천로4안길 55 2층',
	'02-522-1276',
	'퓨전음식',
	'12:00 ~ 21:00',
    'http://instagram.com/nammiplantlab',
    'https://v4.map.naver.com/?mpx=3ccba07ee83de0bafd2259c4be58e338c29eefb98c63a7508a7ec9529e915ed3ad3fe543aa498ab078b6f898c5bc37c1623a92ad1b172dd46b2543702ede2707&searchCoord=8cedd228626895149afa3d7d2b06f305afbc0aab0fcf1d080f70dcb22fcd8a82&query=64Ko66%2B47ZSM656c7Yq4656p&menu=location&tab=1&lng=046d30219126a5e6e5abd252827d824d&mapMode=0&lat=a8bb00fb57b046e0216789e50dd18627&dlevel=8&enc=b64'
	 );

INSERT INTO shop(shop_num, shop_name, shop_address, shop_tel, shop_type, shop_time, shop_hp, shop_map )
VALUES(  4,
   	'르봉땅',
   	'서울 마포구 월드컵로21길 18 1층',
  	'02-332-1351',
   	'카페,디저트',
   	'12:00 ~ 21:30',
   	'https://blog.naver.com/petitelea',
    'https://v4.map.naver.com/?searchCoord=511c9f0b8ca32792d2c7d3e530b8d87f757412c41454a8b9bb2348ecad8fd558&query=66W067SJ65WF&menu=location&tab=1&lng=7bff66ba50250ed1db91d95318bd7c53&mapMode=0&mpx=27bd1582399ce1b24bd7fd0d63daf5380bd10003909539be3a4dc89ab9fe3176b0381e9a370763cb52bd8c65581f443668e99fac46d316f98c3ae9b7d67d597e&lat=6ad9ad98038faeec8014f6b20b585656&dlevel=12&enc=b64'
    
    );    
   
INSERT INTO shop(shop_num, shop_name, shop_address, shop_tel, shop_type, shop_time, shop_hp, shop_map)
VALUES(  5,
   	'비건키친',
   	'서울 서대문구 연희로26가길 15 장영빌딩 2층',
   	'0507-1325-705',
   	'레스토랑',
   	'11:30 ~ 21:00',
  	'https://www.instagram.com/vegankitchen_official/',
    'https://v4.map.naver.com/?searchCoord=e89be9a77464fe48aafa064698ab6db67e4ae2108e168d4180501fd8715aad5c&query=67mE6rG07YKk7Lmc&menu=location&tab=1&lng=67ffd901d1c6d3224130b4570bdfd26a&mapMode=0&mpx=d5ccf29f0b16d64deacb0f09d74c9d41b15057b03502b1e49ad914aacc8ff4df92e1cfe0db9ad8d360c0e0d7067a0c44423ec7e4a5317a87600d39cea1a2d2d7&lat=375d9f8e91d425b2fcc212bc9b5dfdad&dlevel=12&enc=b64'
    ); 

INSERT INTO shop(shop_num, shop_name, shop_address, shop_tel, shop_type, shop_time, shop_hp, shop_map)
VALUES(  6,
	'단미 트레이', 
	'인천시 부평구 길주로585번길 7-19 1',
	'032-218-2018', 
	'비건 카페·디저트',
	'매일 11:00~20:00','https://www.instagram.com/danmi_tray',
    'https://v4.map.naver.com/?searchCoord=5be91ebd750bed7f8121163de9cee816b12f66535e48e70499919019f1b48063&query=64uo66%2B47Yq466CI7J20&menu=location&tab=1&lng=63a55bf084d696d3109a54e26c4eace9&mapMode=0&mpx=12019d5cf93e9165f8d22c5c45d55db76d12e1f674897fad1190d17c17a288b3fd9783e058ae98326e99bd606811854867476952342ad96e0ef6bde239b4d575&lat=065c9e2286c4f5e40df469dfd0e69aa1&dlevel=12&enc=b64'
    );

INSERT INTO shop(shop_num, shop_name, shop_address, shop_tel, shop_type, shop_time, shop_hp, shop_map)
VALUES(  7,
	'엘밭', 
	'인천시 남동구 문화서로3번길 28-1 102호',
	'0507-1368-9937', 
	'비건 베이커리',
	'매일 12:00 - 19:00',
	'https://smartstore.naver.com/elbat',
    'https://v4.map.naver.com/?mpx=8465279076635f81f2b7c0d03fbfffc761aa300879795aedbf973a9157acd0287004c52a70c03b6cca0350673892882de32ac96d036ca438e1fb895fd8d8207d&searchCoord=20da42191192ef20c5109b124a9a97805dd584c375b5f07ed98f3d3a990ddaf5&query=7JeY67Ct&menu=location&tab=1&lng=5f611b16d12df3ea1ce830407a1608f6&mapMode=0&lat=e538fd1dd7ec5a0f56974cb1ac61b1c6&dlevel=12&enc=b64'
    );

INSERT INTO shop(shop_num, shop_name, shop_address, shop_tel, shop_type, shop_time, shop_hp, shop_map)
VALUES(  8,
	'더 비기닝', 
	'인천 중구 개항로 81 1층',
	'0507-1330-0619', 
	'비건 레스토랑',
	'매일 11:30~22:00 (월 휴무)',
    'http://instagram.com/_the__beginning',
    'https://v4.map.naver.com/?mpx=84d707b07605200d0deb4d0faa1307ed2a497c7dd6d643951e1595a51de351b7bc4089c3596da3cc39a7f3ab42a39458e4a9694b0bb63f746a760b252d8073c5&searchCoord=a678affa1223eb349f546c44f24c615c74a1c846947aaab932bd0542c1b016a5&query=642UIOu5hOq4sOuLnQ%3D%3D&menu=location&tab=1&lng=a9a4c0308b8364d1f4903bab38b5bceb&mapMode=0&lat=544831716673f5731e9cef646b6f9de3&dlevel=12&enc=b64'
    );

INSERT INTO shop(shop_num,shop_name,shop_address,shop_tel,shop_type,shop_time,shop_hp, shop_map)
VALUES ( 9,
	'스타일 비건','서울 강남구 학동로43길 38',
	'1800-2361',
	'햄버거',
	'12:00-00:00',
	'https://stylevegan.modoo.at/',
    'https://v4.map.naver.com/?searchCoord=5b1b654e83aba65e346c01e8b7d816bc04192fac95d06b35b646f5bf90b11e24&query=7Iqk7YOA7J2867mE6rG0&menu=location&tab=1&lng=11cae0d908d7d71b2a9dcc6ba87d184c&mapMode=0&mpx=b7725790e345b8dcb8a1781f9a026c448636e44afddc6dc204d1a3d512641430c255551f545290a3788e722b126fe6dac04ce83de1bc110c40b00d49cf052d2f&lat=a2db57cb9309af2869f34a7ef3cd93d3&dlevel=12&enc=b64'
    );


INSERT INTO shop(shop_num,shop_name,shop_address,shop_tel,shop_type,shop_time,shop_hp, shop_map)
VALUES ( 10,
	'행복 비건식당',
	'경남 창원시 마산합포구 3·15대로 187',
	'0507-1360-4088',
	'한식',
	'8:30-21:00',
	'https://smartstore.naver.com/fortunate_greenfood',
    'https://v4.map.naver.com/?searchCoord=7953cbdb17cb38ec9471ecfa40a298ca75ea4cab331836f4413d11b21d1a6533&query=7ZaJ67O167mE6rG0&menu=location&tab=1&lng=ec1d5f688b2a38c03019a9f761055764&mapMode=0&mpx=75d75195894fc1ea7811e18a8bcdfc5a3e466ee2c3e19f6c17d461e5465fedb27889e9ec676840ed261d49ba21170c2342abc90905913f480b635932329ce512&lat=5d7405251e9fa16ec3010cf53ee08f8f&dlevel=12&enc=b64'
    );

INSERT INTO shop(shop_num,shop_name,shop_address,shop_tel,shop_type,shop_time,shop_hp, shop_map)
VALUES ( 11,
	'골든후무스',
	'경기 수원시 영통구 덕영대로 1566 더판타지움 3층',
	'0507-1365-5687',
	'샐러드',
	'9:00-21:00',
	'http://www.etcfood.co.kr/',
    'https://v4.map.naver.com/?mpx=6dae2b652a8a9a6d9903745e85840bc2dcea9e40c27fecec8ffb79d1e94692f39e9bd30e9a83d4ca222fd2a9b9d587273ed039857938026f1e25f4fb570f6bd8&searchCoord=39d5bf2eca2e329a1f842bccc0b5695d49537c4880afd0ac0bb23cb2b755087d&query=6rOo65Og7ZuE66y07Iqk&menu=location&tab=1&lng=32f6a20731ccab6eac18f8c08ed58706&mapMode=0&lat=24fc4e6df95fb67f4ad8a28eff90a047&dlevel=12&enc=b64'
    );
    
    
INSERT INTO review(rev_num, id, shop_num, text, rating)
VALUES(1, 'asgkk', 1, '비건 되고나서 처음 먹는 리조또ㅠㅠ 넘 행복한맛이었습니다 :-)', 5);
INSERT INTO review(rev_num, id, shop_num, text, rating)
VALUES(2, 'skdjfk11', 1, '담백하니 맛있습니다! (다만, 가격이 좀 있네요ㅠ)', 4);
INSERT INTO review(rev_num, id, shop_num, text, rating)
VALUES(3, 'dndb1001', 1, '갤러리 같은 분위기에 음식도 깔끔하고 건강한 맛있는 맛!!', 4);
INSERT INTO review(rev_num, id, shop_num, text, rating)
VALUES(4, 'j12jp', 1, '나오자마자 먹느라고 사진은 못찍었지만 토마토 레몬 파스타 꼭 드세요', 4);
INSERT INTO review(rev_num, id, shop_num, text, rating)
VALUES(5, 'gkgks', 1, '너무 맛있게 잘 먹었습니다! 분위기도 아뜰리에 같았어요!:)', 5);


INSERT INTO review(rev_num, id, shop_num, text, rating)
VALUES(6, 'snrnk', 2, '맛있어요~ 재료가 건강해서 좋아요ㅎㅎ', 5);
INSERT INTO review(rev_num, id, shop_num, text, rating)
VALUES(7, 'sjkdjfk00', 2, '완죠니 재방문각! 비건이라 소화두 잘되구 넘나 좋아용! ', 5);
INSERT INTO review(rev_num, id, shop_num, text, rating)
VALUES(8, 'wew171', 2, '비건디저트 잘 몰랐는데, 맛나네요! 밀가루 No-! 버터 No-! 설탕 No-! ', 4);
INSERT INTO review(rev_num, id, shop_num, text, rating)
VALUES(9, 'gkd32', 2, '아 진짜 너무 맛있어요..먹을때마다 행복하고..제 통장 가지세요', 5);

INSERT INTO review(rev_num, id, shop_num, text, rating)
VALUES(10, 'vnvbd', 3, '웨지감자 존맛탱구리', 5);
INSERT INTO review(rev_num, id, shop_num, text, rating)
VALUES(11, 'kkeiy', 3, '여기 가서 치즈야채피자랑 알리오올리오 안시키는 사람이랑 겸상안함', 5);
INSERT INTO review(rev_num, id, shop_num, text, rating)
VALUES(12, 'bood3', 3, '퓨전 음식 맛집. 비건메뉴만 판매하는 비건 식당이라 좋음', 3);

INSERT INTO review(rev_num, id, shop_num, text, rating)
VALUES(13, 'we22', 4, '첫 방문이에요. 비건 스콘은 첨인데 맛이 좋네요! 쑥음료도 정말 맛있었어요.', 4);
INSERT INTO review(rev_num, id, shop_num, text, rating)
VALUES(14, 'gkdys', 4, '비건 참치 샌드위치 미쳤다리.', 5);

INSERT INTO review(rev_num, id, shop_num, text, rating)
VALUES(15, 'sghkw2', 5, '깔끔한 가게 내부와 신선한 재료들로 만든 맛있는 음식! 또 방문하고 싶어용 잘 먹었습니당.', 5);
INSERT INTO review(rev_num, id, shop_num, text, rating)
VALUES(16, 'tnsdhr', 5, '베리베리 나이스', 5);
INSERT INTO review(rev_num, id, shop_num, text, rating)
VALUES(17, 'gkzlgkzl', 5, '비건은 아니지만 누구나 즐길 수 있는 메뉴인것 같아요. 비건 맥주 맛있어요.', 4);

INSERT INTO review(rev_num, id, shop_num, text, rating)
VALUES(18, 'eksal123', 6, '세상 존맛탱구리♡', 5);
INSERT INTO review(rev_num, id, shop_num, text, rating)
VALUES(19, 'ghgh1', 6, '우리동네 커피맛집으로 인정', 4);
INSERT INTO review(rev_num, id, shop_num, text, rating)
VALUES(20, 'bkbkdy', 6, '연어덮밥이랑 콥 샐러드 먹었는데 넘 맛있어요 야채도 신선하고 맛있음!!', 5);
INSERT INTO review(rev_num, id, shop_num, text, rating)
VALUES(21, 'gigiii', 6, '최고최고 너무 맛있어요 인테리어도 너무이뻐요.', 5);

INSERT INTO review(rev_num, id, shop_num, text, rating)
VALUES(22, 'dpoul', 7, '밀가루는 더부룩 하지만 빵 못잃어 하는 분들에게 추천!', 5);
INSERT INTO review(rev_num, id, shop_num, text, rating)
VALUES(23, 'liliyyyy', 7, '엘밭의 매력에 빠져서 손을 멈추지 못하고 있는 1인이에요...', 5);
INSERT INTO review(rev_num, id, shop_num, text, rating)
VALUES(24, 'seulseul', 7, '비건빵사랑인데 엘밭 크럼블은 진짜 환상ㅠ-ㅠ', 5);
INSERT INTO review(rev_num, id, shop_num, text, rating)
VALUES(25, 'summer77', 7, '건강한 재료로 만들었다는 건 확실히 느껴져요ㅠ', 4);

INSERT INTO review(rev_num, id, shop_num, text, rating)
VALUES(26, 'xxxoxo', 8, '여기 비건 음식점아니고 진짜 그냥 맛집입니다 ㅋㅋ', 5);
INSERT INTO review(rev_num, id, shop_num, text, rating)
VALUES(27, 'bboo22', 8, '비건재료로 이렇게 맛난 요리를 해주시다니.. 셰프님 당쉰은 천재...? 먹잘알...?', 5);
INSERT INTO review(rev_num, id, shop_num, text, rating)
VALUES(28, 'yywyw', 8, '비건이라서 호불호가 있을줄 알았는데 너무 맛있게 폭식했네요! ', 5);

INSERT INTO review(rev_num, id, shop_num, text, rating)
VALUES(29, 'qorhq', 9, '유명 샐러드 맛집이에요! 사장님 적게 일하고 마니 버세요. ', 5);
INSERT INTO review(rev_num, id, shop_num, text, rating)
VALUES(30, 'pigpig99', 9, '레몬 치킨 샌드위치 안 먹은 사람 없게 해주세요ㅠㅠㅠ', 5);
INSERT INTO review(rev_num, id, shop_num, text, rating)
VALUES(31, 'fnvlvl', 9, '덕분에 칼로리 걱정없이 다이어트 하고 있어요!', 4);

INSERT INTO review(rev_num, id, shop_num, text, rating)
VALUES(32, 'shffoshfo', 10, '#비건#무오신체피자#수제버거#웨지감자 채식인데도 먹으니까 힘나요!', 5);
INSERT INTO review(rev_num, id, shop_num, text, rating)
VALUES(33, 'whywhy', 10, '사장님 맛잘알...모든 메뉴가 다 맛있어요', 5);

INSERT INTO review(rev_num, id, shop_num, text, rating)
VALUES(34, 'lololoy', 11, '매콤콩불구이덮밥 채개장 다 맛있는데 매콤콩불구이덮밥 진짜!!', 5);
INSERT INTO review(rev_num, id, shop_num, text, rating)
VALUES(35, '1006ds', 11, '들깨탕 추천합니다. 국물도 정말 진하고 좋습니다.', 5);
INSERT INTO review(rev_num, id, shop_num, text, rating)
VALUES(36, 'gsg9980', 11, '건강한 한끼 맛있게 먹었습니다. 콩불구이 덮밥 너무 맛있네요.', 4);