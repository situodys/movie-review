import {Box, Link, Pagination, Stack} from "@mui/material";
import axios from "axios";
import {API_BASE_URL} from "../config/config";
import {useEffect, useState} from "react";

export default function MyPagination(props) {

    const [listIndex, setListIndex] = useState(0);


    function handleClick(e, idx) {
        e.preventDefault();
        console.log(typeof (idx));
        setListIndex(idx);
    }

    useEffect(() => {
        getMovieLists()
    }, [listIndex]);

    const getMovieLists = () => {
        console.log(listIndex);
        if (!props.movieLists)
            return;
        console.log(props.movieLists.totalResultsCount);

        axios({
            method: 'get',
            url: API_BASE_URL + `/api/movie/list?page=${listIndex}&totalCount=${props.movieLists.totalResultsCount}&${props.searchType}=${props.searchKeyword}`,
            dataType: 'json'
        })
            .then(function (response) {
                console.log(response.data);
                props.handleMovieLists(response.data);
            })
            .catch(function (error) {
                console.log(error);
            });
    }

    return (
        <Stack justifyContent={"center"} direction={"row"} textAlign={"center"} marginTop={"30px"}>
            {props.movieLists && props.movieLists.prev &&
                <Link
                    onClick={(e) => handleClick(e, props.movieLists.startIndex - 1)}
                    style={{fontSize: "x-large", width: "50px", height: "50px"}}>prev</Link>
            }
            {props.movieLists && props.movieLists.pageList && props.movieLists.pageList.map((idx) => (
                <Link
                    onClick={(e) => handleClick(e, idx)}
                    style={{
                        fontSize: "x-large",
                        width: "50px",
                        height: "50px",
                        color: idx == props.movieLists.curPage ? "white" : "#2196f3",
                        backgroundColor: idx == props.movieLists.curPage ? "#2196f3" : "white",
                        border: "0.1px dashed grey"
                    }}
                >{idx}</Link>
            ))
            }
            {props.movieLists && props.movieLists.next &&
                <Link
                    onClick={(e) => handleClick(e, props.movieLists.endIndex + 1)}
                    style={{fontSize: "x-large", width: "50px", height: "50px"}}>next</Link>
            }
        </Stack>
    )
};