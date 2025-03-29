const paths = [
  {
    id: 1,
    lable: "Trang chu",
    path: "/",
  },
  {
    id: 2,
    lable: "Gioi thieu",
    path: "/about",
  },
  {
    id: 3,
    lable: "Danh muc",
    path: "/category",
    sub: [
      {
        id: 4,
        lable: "Tea",
        path: "/category/tea",
      },
      {
        id: 5,
        lable: "Coffe",
        path: "/category/coffe",
      },
    ],
  },
];

export default paths;
