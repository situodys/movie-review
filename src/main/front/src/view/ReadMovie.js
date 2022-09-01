import {useHistory, useLocation} from "react-router-dom";
import {useEffect, useState} from "react";
import axios from "axios";
import {API_BASE_URL} from "../config/config";
import {Button, Container, TextField, Typography} from "@mui/material";
import UploadedImages from "../component/UploadedImages";

export default function ReadMovie() {
    const location = useLocation();
    const [movieDetail, setMovieDetail] = useState();




    useEffect(() => {
        console.log(location.state.mno);
        getMovieDetailInfo();
    }, []);

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

    return (
        <Container maxWidth={"md"} background={"#f5f5f5"}>
            <Typography variant={"h2"} color={"inherit"} paddingTop={5} paddingBottom={5}>Movie Info
                Page</Typography>
            <Typography variant={"h3"}>Title: {movieDetail.movieDTO.title}</Typography>
            <Typography variant={"h5"}>ReviewCount: {movieDetail.reviewCount}</Typography>
            <Typography variant={"h5"}>Rating: {movieDetail.rating}</Typography>

            <UploadedImages images={movieDetail.movieDTO.movieImageDTOList}/>



        </Container>

    );

};