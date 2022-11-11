# Nhóm 19 - INT 3120 40

## README    

# Thành viên NHÓM 19: 
* Trần Long Dũng - 19021245
* Chu Thành Tùng - 19021386
* Bùi Đăng Nam Bình - 19021225

# Week 1: Hello world


![image](https://user-images.githubusercontent.com/62632444/190583727-120a6125-c1f7-4ba7-8487-20448ed0088f.png)

----

# Week 2: Figma + Hello World với tên riêng

https://www.figma.com/file/4cuFJfVI6p00AQvrP6V7zd/MOMO?node-id=0%3A1

----

# Week 3: Demo giao diện đầu tiên
![IMG_0708](https://user-images.githubusercontent.com/62632444/193206758-0523a60a-fdb4-44cd-8fc5-5b993c1a264f.gif)

----

# Week 4: 
* Thay đổi Logo ứng dụng
* Splash -> Login -> Home page
* Màn Splash delay 2s sau đó chuyển qua màn hình đăng nhập để đăng nhập
* Bấm button Xác nhận sẽ chuyển tới màn Home
* Màn Home sử dụng Listview

![giphy](https://user-images.githubusercontent.com/62632444/193201226-9ce2caa0-bf6a-4576-aea1-d5022397bb54.gif)

----

# Week 5:
* Xin quyền và mở Camera mặc định của máy
* Hoàn thiện các màn hình đã vẽ trong Figma

Do tệp lớn hơn 10MB nên chúng em để link gif tại đây ạ
https://giphy.com/gifs/jGhfyZ2OkjmWTOYDuK

# Week 6: 
* Đăng nhập bằng Firebase thông qua số điện thoại và mã OTP
* Hoàn thiện các Layout chính

----

# Week 7:
* Kiểm thử mức độ bao phủ bằng YOYO

![308010260_529091595708642_2855827236496903055_n](https://user-images.githubusercontent.com/62632444/197124246-b9bc25e1-de8c-4657-ae0d-87f718993362.png)

* Kiểm thử Monket Testing với clicks = 500

![300199828_4856544457807759_4109099745922782881_n](https://user-images.githubusercontent.com/62632444/197124338-8332e324-7dda-4e7f-9c78-036cf3b8a106.png)

----

# Week 8:
* Require Network khi sử dụng ứng dụng
* Sử dụng Firebase Auth, validate user trong vòng 15 phút không cần đăng nhập lại nếu đã đăng nhập thành công trước đó
* update giao diện bên trong ứng dụng

https://giphy.com/gifs/zvXnEAFdlNnFBfEvpd
(Do tập GIF lớn hơn 10MB nên chúng em đính kèm link tại đây ạ)
----
# Week 9: Unit TEST

* Kiểm thử Unit test run with com

![image](https://user-images.githubusercontent.com/62632444/199882613-8ddc22eb-6e05-4ab6-ae99-3af7f4c56691.png)

* Kiểm thử Unit test run with coverage

![image](https://user-images.githubusercontent.com/62632444/199882667-d81ede68-344a-4d64-a755-4f224d651104.png)

* Khi tiến hành kiểm thử Coverage test, hệ thống báo đã passed 9 test case, nhưng coverage lại báo project files out of date và cho kết quả cov 0%
* Nhóm chúng em tiến hành tìm hiểu trên các diễn đàn trên mạng với lỗi trên, sau khi tìm hiểu nhóm nhận thấy đây là một lỗi mới với kotlin và chưa thấy có hướng giải quyết và cũng đã thấy nhiều trường hợp tương tự trên các diễn đàn

* Các diễn đàn nhóm đã tham khảo
* https://youtrack.jetbrains.com/issue/KTIJ-981
* https://youtrack.jetbrains.com/issue/IDEA-194073/Gradle-Run-with-Coverage-triggers-IDEA-JPS-build-Delegate-IDE-buildrun-actions-to-Gradle-setting-is-ignored#focus=Comments-27-6180937.0-0
* https://stackoverflow.com/questions/73273807/android-studio-coverage-tool-says-that-project-is-out-of-date

* Kết quả nhận được khi chạy
![image](https://user-images.githubusercontent.com/62632444/199885145-d0d90577-e816-4805-9cf6-10ba29a6c7f2.png)


* Nhóm sẽ tiếp tục tìm hiểu, và thực hiện lại Unit test 

----
# Week 10: Fix bug và làm thêm database

* Nhóm đã tìm ra hướng giải quyết Cov Report với kotlin, thêm testOption on Build.gradle (module level) của app sau đó config và đã đạt được kết quả
* Đây là Cov report của nhóm tuần này, những tuần tới nhóm sẽ tiếp tục cải thiện test case và update ứng dụng

![image](https://user-images.githubusercontent.com/62632444/201156969-aa7a5ebd-262b-4062-a02d-a1a46af8f7e9.png)
![image](https://user-images.githubusercontent.com/62632444/201157381-f2724245-9d97-430c-ac0f-8ff548c8be3a.png)


* Thêm database và sử dụng Firestore, tạo bảng data trên Firestore, tạo cái model để lấy dữ liệu trả về từ Firestore

![image](https://user-images.githubusercontent.com/62632444/201157066-11c52cf6-311a-44fa-a7c1-468f18104ca1.png)


* Đăng kí vơi số điện thoại
* Update thêm màn đăng kí
* Các màn hình nhóm đã làm
![image](https://user-images.githubusercontent.com/62632444/201278112-3020b20e-b06d-46a0-b765-d204445961d4.png)
![image](https://user-images.githubusercontent.com/62632444/201278145-84275a30-7643-4d54-8339-c8690fec01ff.png)
![image](https://user-images.githubusercontent.com/62632444/201278159-c8e6e1fd-bdf6-403c-a988-80b5b2f4dc7f.png)
![image](https://user-images.githubusercontent.com/62632444/201278203-39379703-21b4-4ab4-b0a3-8036a02cec82.png)
![image](https://user-images.githubusercontent.com/62632444/201278253-10bc709f-80bb-4e0b-aadb-94cc3aa82ebe.png)
![image](https://user-images.githubusercontent.com/62632444/201278227-f9c7473a-3b7d-4f29-912c-5e69b6265fd8.png)

 






