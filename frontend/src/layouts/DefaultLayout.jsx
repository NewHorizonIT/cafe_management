import { Footer, Header } from "@/components/layout";
import { links, socialLinks } from "@/constants/footer";
import React from "react";
import { Outlet } from "react-router-dom";

const DefaultLayout = () => {
  return (
    <div className="bg-base-100 min-h-screen h-max">
      <Header />
      <main className="pt-[60px] bg-base-200">
        <Outlet />
      </main>
      <Footer
        links={links}
        socialLinks={socialLinks}
        logo={<h1 className="font-playwrite text-4xl">MyCoffe</h1>}
      />
    </div>
  );
};

export default DefaultLayout;
