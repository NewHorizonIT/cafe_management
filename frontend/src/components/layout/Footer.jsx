import React from "react";

const Footer = ({ links, socialLinks, logo }) => {
  return (
    <footer class="bg-neutral text-neutral-content p-10 ">
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
          <h6 class="footer-title">Social</h6>
          <div class="grid grid-flow-row gap-4">
            {links.map((link) => (
              <a href={link.link}>{link.lable}</a>
            ))}
          </div>
        </nav>
        <nav>
          <h6 class="footer-title">Social</h6>
          <div class="flex gap-4">
            {socialLinks.map((item) => (
              <a href={item.link}>{item.icon}</a>
            ))}
          </div>
        </nav>
      </div>
    </footer>
  );
};

export default Footer;
