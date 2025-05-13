import React from "react";

const About = () => {
  return (
    <div className="about-page bg-gray-100 min-h-screen">
      <header className="bg-blue-600 text-white py-10 text-center">
        <h1 className="text-5xl font-bold">Về chúng tôitôi</h1>
        <p className="text-lg mt-2 max-w-[600px] mx-auto">
          My Coffe là 1 cửa hàng cafe được thành lập vào năm 2020. Ở đây chung
          tôi ngoài cung cấp đồ uống cafe chúng tôi còn cung cấp các loại thức
          uống khác với chất lượng cao vào giá cả hợp lý.
        </p>
      </header>

      <main className="container mx-auto py-10 px-4">
        <section className="mb-10">
          <h2 className="text-3xl font-semibold text-center mb-6">
            Nhiệm vụ của chúng tôi
          </h2>
          <p className="text-gray-700 text-center max-w-2xl mx-auto">
            Sứ mệnh của chúng tôi là cung cấp cà phê tuyệt vời và dịch vụ xuất
            sắc với giá cả hợp lý. Chúng tôi luôn ưu tiên sự hài lòng của khách
            hàng lên hàng đầu. Luôn lắng nghe nhưng ý kiến của khách hàng để cải
            thiện.
          </p>
        </section>

        <section className="grid grid-cols-1 md:grid-cols-2 gap-8 items-center mb-10">
          <div>
            <h2 className="text-3xl font-semibold mb-4">
              Câu chuyện của chúng tôi
            </h2>
            <p className="text-gray-700">
              Chúng tôi bắt đầu hành trình này với niềm đam mê và khát vọng tạo
              ra sự khác biệt. Mỗi bước đi là một câu chuyện, mỗi thử thách là
              một cơ hội để phát triển và cống hiến nhiều hơn cho khách hàng.
            </p>
          </div>
          <div>
            <img
              src="https://images.unsplash.com/photo-1521017432531-fbd92d768814?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
              alt="Our Story"
              className="rounded-lg shadow-lg w-full"
            />
          </div>
        </section>

        <section className="text-center">
          <h2 className="text-3xl font-semibold mb-6">Đội ngũ của chúng tôi</h2>
          <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-6">
            <div className="team-member">
              <img
                src="https://th.bing.com/th/id/OIF.PD4POJClyhktIIN9iIZeCg?cb=iwc1&rs=1&pid=ImgDetMain"
                alt="Team Member"
                className="rounded-full mx-auto mb-4 w-[200px] h-[200px]"
              />
              <h3 className="text-xl font-medium">Nguyễn Huy Bảo</h3>
              <p className="text-gray-600">Co-Founder</p>
            </div>
            <div className="team-member">
              <img
                src="https://m.media-amazon.com/images/S/amazon-avatars-global/2cf723d4-338f-4373-ab50-c08e51b09f87._CR0%2C0%2C441%2C441_UX460_.jpg"
                alt="Team Member"
                className="rounded-full mx-auto mb-4 w-[200px] h-[200px]"
              />
              <h3 className="text-xl font-medium">Đặng Thị Thu Hiền</h3>
              <p className="text-gray-600">HR</p>
            </div>
            <div className="team-member">
              <img
                src="https://th.bing.com/th?id=OIF.O%2bj5BKbWHlDJITUca47v5A&cb=iwc1&rs=1&pid=ImgDetMain"
                alt="Team Member"
                className="rounded-full mx-auto mb-4 w-[200px] h-[200px]"
              />
              <h3 className="text-xl font-medium">Võ Văn Cường</h3>
              <p className="text-gray-600">Designer</p>
            </div>
          </div>
        </section>
      </main>
    </div>
  );
};

export default About;
