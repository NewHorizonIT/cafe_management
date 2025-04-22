const paths = [
  {
    id: 1,
    lable: "Trang chủ",
    path: "/",
  },
  {
    id: 2,
    lable: "Giới thiệu",
    path: "/about",
  },
  {
    id: 3,
    lable: "Danh mục",
    path: "/",
    sub: [
      {
        id: 4,
        lable: "Trà",
        path: "/tea",
      },
      {
        id: 5,
        lable: "Coffee",
        path: "/coffee",
      },
    ],
  },
];

export default paths;
