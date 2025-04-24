import React from "react";

const Footer = ({ links, socialLinks, logo }) => {
  return (
    <footer className="bg-neutral text-neutral-content p-10 ">
      <div className="max-w-[1180px] w-100% mx-auto grid grid-cols-1 sm:grid-cols-3 justify-between gap-5">
        <aside>
          <div>{logo}</div>
          <p>
            Mycoffee shop Ltd.
            <br />
            Cup cấp đồ uống chất lượng
          </p>
        </aside>
        <nav>
          <h6 className="footer-title">Dịch vụ</h6>
          <div className="grid grid-flow-row gap-4 list-none cursor-pointer">
            {links.map((link, index) => (
              <li key={index}>
                <a href={link.href}>{link.lable}</a>
              </li>
            ))}
          </div>
        </nav>
        <nav>
          <h6 className="footer-title">Mạng xã hội</h6>
          <div className="flex gap-4">
            {socialLinks.map((item, i) => (
              <a href={item.link} key={i}>
                {item.icon}
              </a>
            ))}
          </div>
        </nav>
      </div>
    </footer>
  );
};

export default Footer;
