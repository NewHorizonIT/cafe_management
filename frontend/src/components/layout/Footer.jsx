import React from "react";

const Footer = ({ links, socialLinks, logo }) => {
  return (
    <footer className="bg-neutral text-neutral-content p-10 ">
      <div className="max-w-[1180px] w-100% mx-auto grid grid-cols-1 sm:grid-cols-3 justify-between gap-5">
        <aside>
          <div>{logo}</div>
          <p>
            ACME Industries Ltd.
            <br />
            Providing reliable tech since 1992
          </p>
        </aside>
        <nav>
          <h6 className="footer-title">Social</h6>
          <div className="grid grid-flow-row gap-4">
            {links.map((link, index) => (
              <li key={index}>
                <a href={link.href}>{link.label}</a>
              </li>
            ))}
          </div>
        </nav>
        <nav>
          <h6 className="footer-title">Social</h6>
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
