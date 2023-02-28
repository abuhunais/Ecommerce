import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';


export default function Home() {





    const [prod, setProd] = useState([])
    const [cartItems, setCartItems] = useState([]);
    const [orderedItems, setOrderedItems] = useState([]);
    // const [reviews, setReviews] = useState([]);




    const navigate = useNavigate();


    useEffect(() => {
        loadProd();
        loadCartItems();
        loadOrderedItems();
        // loadReviews();

    }, []);
    const loadProd = async () => {
        const result = await axios.get("http://localhost:9876/products/all")
        setProd(result.data);
    };

    // const loadReviews = async (productId) => {
    //     const result = await axios.get(`http://localhost:8092/reviews/${productId}`)
    //     setReviews(result.data);
    // };

    const loadCartItems = async () => {
        const result = await axios.get("http://localhost:9876/cart/all")
        setCartItems(result.data);
    };

    const loadOrderedItems = async () => {
        const result = await axios.get("http://localhost:9876/orders");
        setOrderedItems(result.data);
    };

    const addToCart = async (id) => {
        await axios.post(`http://localhost:9876/cart/${id}`);
        loadCartItems(); // reload cart items after adding a new item
        // navigate('/OderList');
    };
    const createOrder = async (customerId, productId) => {
        const result = await axios.post(`http://localhost:9876/order/${customerId}/${productId}`);
        const orderedProduct = result.data;
        navigate('/order-list', { state: { orderedItems: [...orderedItems, orderedProduct] } });
        loadOrderedItems(); // reload ordered items after creating a new order
    };

    const deleteProduct = async (id) => {
        await axios.delete(`http://localhost:9876/products/${id}`);
        // Reload the product list after deleting a product
        loadProd();
    };

    // const addProductReview = async (productId, review) => {
    //     review.productId = productId; // add the product ID to the review object
    //     const result = await axios.post(`http://localhost:8092/reviews/${productId}`, review);
    //     setReviews([...reviews, result.data]);
    // };


    // const updateProductReview = async (reviewId, comment, rating) => {
    //     const result = await axios.put(`http://localhost:8092/reviews/${reviewId}`, { comment, rating });
    //     const updatedReviews = reviews.map(review => review.id === reviewId ? result.data : review);
    //     setReviews(updatedReviews);
    // };













    return (
        <div className='container'>
            <div className='py-4'>
                <table className="table border shadow">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Name</th>
                            <th scope="col">Description</th>
                            <th scope="col">Category</th>
                            <th scope="col">SubCategory</th>
                            <th scope="col">Stock</th>
                            <th scope="col">Price</th>
                            <th scope="col">Reviews</th>
                            <th scope="col">Inventory Report</th>
                            <th scope="col">Action</th>
                        </tr>
                    </thead>
                    <tbody>

                        {
                            prod.map((pro, index) => (
                                <tr>
                                    <th scope="row" key={index}>{index + 1}</th>
                                    <td>{pro.name}</td>
                                    <td>{pro.description}</td>
                                    <td>{pro.category}</td>
                                    <td>{pro.subcategory}</td>
                                    <td>{pro.stock}</td>
                                    <td>{pro.price}</td>
                                    <td>{pro.reviews}</td>
                                    <td>{pro.inventoryReport}</td>
                                    <td>
                                        {/* {reviews.filter(review => review.productId === pro.id).map(review => (
                                            <div key={review.id}>
                                                <p>{review.comment}</p>
                                                <p>{review.rating}</p>
                                                <button onClick={() => updateProductReview(review.id, "new comment", 5)}>Update</button>
                                            </div>
                                        ))}
                                        <form onSubmit={event => { event.preventDefault(); addProductReview(pro.id, { comment: event.target.comment.value, rating: 4 }); event.target.reset(); }}>
                                            <input type="text" placeholder="Enter comment" name="comment" />
                                            <button type="submit">Add Review</button>
                                        </form> */}
                                        <button className='btn btn-primary mx-2'>View</button>
                                        <Link className="btn btn-outline-primary mx-2" z
                                            to={`/editproducts/${pro.id}`}
                                        >Edit</Link>
                                        <button
                                            className='btn btn-primary mx-2'
                                            onClick={() => addToCart(pro.id)}
                                        >
                                            Add to cart
                                        </button>

                                        <button
                                            className='btn btn-success mx-2'
                                            onClick={() => createOrder(1, pro.id)}
                                        >
                                            Buy now
                                        </button>
                                        <button className='btn btn-danger mx-2'
                                            onClick={() => deleteProduct(pro.id)}

                                        >Delete</button>

                                    </td>
                                </tr>


                            ))
                        }


                    </tbody>
                </table>
            </div>
            <div>
                <h2> Cart Items</h2>
                <ul>
                    {cartItems.map((prod) => (
                        <li key={prod.productId}>({prod.name}) ({prod.totalPrice})</li>
                    ))}
                </ul>
            </div>
        </div>
    )
}
