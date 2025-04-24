export const orders = [
  {
    id: "ORD001",
    customer: "John Doe",
    status: "Pending",
    total: 25.5,
    items: [
      { name: "Cappuccino", quantity: 1, price: 5.5 },
      { name: "Latte", quantity: 2, price: 10.0 },
    ],
  },
  {
    id: "ORD002",
    customer: "Jane Smith",
    status: "Confirmed",
    total: 40.0,
    items: [
      { name: "Espresso", quantity: 3, price: 15.0 },
      { name: "Dark Coffee", quantity: 2, price: 25.0 },
    ],
  },
  {
    id: "ORD003",
    customer: "Alice Johnson",
    status: "Pending",
    total: 15.75,
    items: [
      { name: "Milk Coffee", quantity: 1, price: 7.75 },
      { name: "Tea Orange", quantity: 1, price: 8.0 },
    ],
  },
  {
    id: "ORD004",
    customer: "Bob Brown",
    status: "Delivered",
    total: 60.0,
    items: [
      { name: "Cappuccino", quantity: 4, price: 22.0 },
      { name: "Latte", quantity: 3, price: 38.0 },
    ],
  },
];

export const columns = [
  {
    header: "Order ID",
    accessor: "id",
  },
  {
    header: "Customer",
    accessor: "customer",
  },
  {
    header: "Status",
    accessor: "status",
  },
  {
    header: "Total",
    accessor: "total",
  },
];
