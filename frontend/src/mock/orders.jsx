export const orders = [
  {
    id: "ORD001",
    customer: "Nguyễn Văn A",
    date: "2025-04-10",
    status: "Đang xử lý",
  },
  {
    id: "ORD002",
    customer: "Trần Thị B",
    date: "2025-04-09",
    status: "Đã giao",
  },
  {
    id: "ORD003",
    customer: "Phạm Văn C",
    date: "2025-04-08",
    status: "Đã huỷ",
  },
];

export const columns = [
  { header: "Mã đơn", accessor: "id" },
  { header: "Khách hàng", accessor: "customer" },
  { header: "Ngày đặt", accessor: "date" },
  {
    header: "Trạng thái",
    render: (row) => (
      <span
        className={`px-2 py-1 text-xs font-semibold rounded ${
          row.status === "Đã giao"
            ? "bg-green-100 text-green-700"
            : row.status === "Đang xử lý"
            ? "bg-yellow-100 text-yellow-700"
            : "bg-red-100 text-red-700"
        }`}
      >
        {row.status}
      </span>
    ),
  },
];
