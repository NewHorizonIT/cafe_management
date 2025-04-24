import React from "react";

const About = () => {
  return (
    <div className="about-page bg-gray-100 min-h-screen">
      <header className="bg-blue-600 text-white py-10 text-center">
        <h1 className="text-5xl font-bold">About Us</h1>
        <p className="text-lg mt-2">
          Learn more about our mission, team, and values
        </p>
      </header>

      <main className="container mx-auto py-10 px-4">
        <section className="mb-10">
          <h2 className="text-3xl font-semibold text-center mb-6">
            Our Mission
          </h2>
          <p className="text-gray-700 text-center max-w-2xl mx-auto">
            Our mission is to empower cafe owners and staff with the technology
            they need to focus on what matters most: delivering great coffee and
            excellent service.
          </p>
        </section>

        <section className="grid grid-cols-1 md:grid-cols-2 gap-8 items-center mb-10">
          <div>
            <h2 className="text-3xl font-semibold mb-4">Our Story</h2>
            <p className="text-gray-700">
              Duis fringilla cursus aliquet. Vivamus tristique velit sed
              tincidunt commodo, arcu purus varius nunc, id accumsan turpis sit
              amet.
            </p>
          </div>
          <div>
            <img
              src="https://source.unsplash.com/400x300/?coffee"
              alt="Our Story"
              className="rounded-lg shadow-lg w-full"
            />
          </div>
        </section>

        <section className="text-center">
          <h2 className="text-3xl font-semibold mb-6">Meet Our Team</h2>
          <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-6">
            <div className="team-member">
              <img
                src="https://source.unsplash.com/400x300/?team"
                alt="Team Member"
                className="rounded-full mx-auto mb-4"
              />
              <h3 className="text-xl font-medium">John Doe</h3>
              <p className="text-gray-600">Co-Founder</p>
            </div>
            <div className="team-member">
              <img
                src="https://source.unsplash.com/400x300/?team"
                alt="Team Member"
                className="rounded-full mx-auto mb-4"
              />
              <h3 className="text-xl font-medium">Jane Smith</h3>
              <p className="text-gray-600">CTO</p>
            </div>
            <div className="team-member">
              <img
                src="https://source.unsplash.com/400x300/?team"
                alt="Team Member"
                className="rounded-full mx-auto mb-4"
              />
              <h3 className="text-xl font-medium">Alice Johnson</h3>
              <p className="text-gray-600">Designer</p>
            </div>
          </div>
        </section>
      </main>

      <footer className="bg-gray-800 text-white py-6 text-center">
        <p>&copy; 2025 Cafe Management. All rights reserved.</p>
      </footer>
    </div>
  );
};

export default About;
