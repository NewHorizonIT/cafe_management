const isNewProduct = (createdAt) => {
  if (!createdAt) return false;

  const createdDate = new Date(createdAt);
  const sevenDaysAgo = new Date();
  sevenDaysAgo.setDate(sevenDaysAgo.getDate() - 7);

  return createdDate >= sevenDaysAgo;
};

export default isNewProduct;
