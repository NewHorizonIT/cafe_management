import { FormUser } from "@/components/forms";
import { Button, Modal, Table } from "@/components/ui";
import React, { useState, useEffect } from "react";

const UserManagement = () => {
  const [users, setUsers] = useState([]);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [currentUser, setCurrentUser] = useState(null);

  useEffect(() => {
    setUsers([
      { id: 1, name: "John Doe", role: "Admin" },
      { id: 2, name: "Jane Smith", role: "Cashier" },
    ]);
  }, []);

  useEffect(() => {
    console.log("Users state initialized:", users);
  }, [users]);

  useEffect(() => {
    console.log("Modal state:", isModalOpen);
  }, [isModalOpen]);

  const handleAddUser = () => {
    console.log("Add User button clicked");
    setCurrentUser(null);
    setIsModalOpen(true);
  };

  const handleEditUser = (user) => {
    console.log("Editing user:", user);
    setCurrentUser(user);
    setIsModalOpen(true);
  };

  const handleDeleteUser = (userId) => {
    console.log("Deleting user with ID:", userId);
    setUsers(users.filter((user) => user.id !== userId));
  };

  const handleSaveUser = (user) => {
    if (user.id) {
      // Update existing user
      setUsers(users.map((u) => (u.id === user.id ? user : u)));
    } else {
      // Add new user
      setUsers([...users, { ...user, id: Date.now() }]);
    }
    setIsModalOpen(false);
  };

  return (
    <div>
      <h1>User Management</h1>
      <Button eventHandler={handleAddUser}>Add User</Button>
      <Table
        data={users}
        columns={[
          { accessor: "name", header: "Name" },
          { accessor: "role", header: "Role" },
          {
            header: "Actions",
            render: (user) => (
              <>
                <Button eventHandler={() => handleEditUser(user)}>Edit</Button>
                <Button eventHandler={() => handleDeleteUser(user.id)}>
                  Delete
                </Button>
              </>
            ),
          },
        ]}
        onRowClick={(user) => handleEditUser(user)}
      />
      {isModalOpen && (
        <Modal
          onClose={() => setIsModalOpen(false)}
          title="User Form"
          isOpen={isModalOpen}
        >
          <FormUser user={currentUser} onSave={handleSaveUser} />
        </Modal>
      )}
    </div>
  );
};

export default UserManagement;
