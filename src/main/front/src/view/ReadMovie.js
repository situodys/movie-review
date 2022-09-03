import {useNavigate, useLocation} from "react-router-dom";
import {useEffect, useState} from "react";
import axios from "axios";
import {API_BASE_URL} from "../config/config";
import {Button, Container, IconButton, TextField, Typography} from "@mui/material";
import UploadedImages from "../component/UploadedImages";
import {Add, ArrowBack, Comment, Search} from "@mui/icons-material";
import MyModal from "../component/MyModal";
import Reviews from "../component/Reviews";

export default function ReadMovie() {
    const location = useLocation();
    let navigate = useNavigate();
    const [movieDetail, setMovieDetail] = useState();
    const [showReview, setShowReview] = useState(false);
    const [showModal, setShowModal] = useState(false);
    const [reviews, setReviews] = useState([]);
    const [needDataSync, setNeedDataSync] = useState(false);

    function handleShowReview () {
        console.log(showReview);
        setShowReview(!showReview);
    }

    function handleShowModal() {
        console.log(showModal);
        setShowModal(true);
    }

    const handleCloseModal= () =>{
        setShowModal(false);
    }

    const isDataChange= () =>{
        setNeedDataSync(!needDataSync);
    }

    useEffect(() => {
        console.log(location.state.mno);
        getMovieDetailInfo();
        getReviews();
    }, [needDataSync]);

    function getMovieDetailInfo() {
        axios({
            method: 'get',
            url: API_BASE_URL + `/api/movie/read?mno=${location.state.mno}`,
            dataType: 'json'
        })
            .then(function (response) {
                console.log(response.data);
                setMovieDetail(response.data);
            })
            .catch(function (error) {
                console.log(error);
            });
    }

    function getReviews() {
        axios({
            method: 'get',
            url: API_BASE_URL + `/api/review/${location.state.mno}/all`,
            dataType: 'json'
        })
            .then(function (response) {
                console.log(response.data);
                setReviews(response.data);
            })
            .catch(function (error) {
                console.log(error);
            });
    }

    return (
        <Container maxWidth={"md"} background={"#f5f5f5"} >
            <Button variant={"contained"} startIcon={<ArrowBack/>} onClick={() =>{navigate(-1)}} size={"small"}>prev</Button>

            <Typography variant={"h2"} color={"inherit"} paddingTop={5} paddingBottom={5}>Movie Info
                Page</Typography>

            <Typography variant={"h3"}>Title: {movieDetail && movieDetail.movieDTO.title}</Typography>
            <Typography variant={"h5"}>ReviewCount: {movieDetail && movieDetail.reviewCount}</Typography>
            <Typography variant={"h5"}>Rating: {movieDetail && movieDetail.rating}</Typography>


            <UploadedImages images={movieDetail && movieDetail.movieDTO.movieImageDTOList}/>
            <Button variant={"contained"} startIcon={<Comment/>} size={"small"} onClick={handleShowReview}>review
                <span style={{color: "white",marginLeft: "5px", width: "20px", height: "20px"} }>{movieDetail && movieDetail.reviewCount}</span>
            </Button>
            <IconButton color={"primary"} size={"large"} onClick={handleShowModal}>
                <input type={"hidden"}/>
                <Add/>
            </IconButton>
            <MyModal open={showModal} mno = {location.state.mno} handleClose ={handleCloseModal} isDataChange={isDataChange}
            dataSync={needDataSync}/>

            {
                showReview && reviews && <Reviews reviews={reviews} isDataChange={isDataChange} dataSync={needDataSync}/>
            }
            {/*<Button variant={"contained"} startIcon={<Add/>} size={"small"}></Button>*/}

        </Container>

    );

};