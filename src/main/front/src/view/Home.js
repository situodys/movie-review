import {useEffect, useRef, useState} from "react";
import axios from "axios";
import {
    Button,
    Container, MenuItem,
    Paper, Table, TableBody,
    TableCell,
    tableCellClasses, TableContainer, TableHead, TableRow, TextField,
    Typography
} from "@mui/material";
import {styled} from '@mui/material/styles';
import {AddBox, Search} from "@mui/icons-material";
import {API_BASE_URL} from "../config/config";
import MyPagination from "../component/MyPagination";
import {Link} from "react-router-dom";

const Home = () => {

    const StyledTableCell = styled(TableCell)(({theme}) => ({
        [`&.${tableCellClasses.head}`]: {
            backgroundColor: theme.palette.common.black,
            color: theme.palette.common.white,
        },
        [`&.${tableCellClasses.body}`]: {
            fontSize: 14,
        },
    }));

    const StyledTableRow = styled(TableRow)(({theme}) => ({
        '&:nth-of-type(odd)': {
            backgroundColor: theme.palette.action.hover,
        },
        // hide last border
        '&:last-child td, &:last-child th': {
            border: 0,
        },
    }));

    const [movieLists, setMovieLists] = useState();
    const [selectItem, setSelectItem] = useState("title");
    const [searchKeyword, setSearchKeyword] = useState("");

    const selectItems = [
        {
            value: "title",
            label: "제목"
        },
        {
            value: "writer",
            label: "작성자"
        }
    ]

    useEffect(() => {
        axios({
            method: 'get',
            url: API_BASE_URL + '/api/movie/list',
            dataType: 'json'
        })
            .then(response => {
                console.log(response.data)
                setMovieLists(response.data)
            })
    }, []);

    const handleMovieLists = (updatedMovieLists) => {
        setMovieLists(updatedMovieLists);
    }

    const handleSelectItem = (e) => {
        console.log(e.target.value);
        setSelectItem(e.target.value);
    }

    const handleSearchKeyword = (e) => {
        console.log(e.target.value);
        setSearchKeyword(e.target.value);
    }
    const handleAddMovie = (e) => {
        e.preventDefault();
        window.location.href = "/movie/register"
    }
    const handleSearch = () => {
        axios({
            method: 'get',
            url: API_BASE_URL + `/api/movie/list?${selectItem}=${searchKeyword}`,
            dataType: 'json'
        })
            .then(function (response) {
                console.log(response.data);
                setMovieLists(response.data);
            })
            .catch(function (error) {
                console.log(error);
            });
    }

    return (
        <Container>
            <Typography variant={"h2"} color={"inherit"} paddingTop={5} paddingBottom={5}>
                Movie List Page
            </Typography>
            <TextField size={""} select label="Search Type" variant="outlined" value={selectItem}
                       onChange={handleSelectItem} style={{width: "100px"}}>
                {selectItems.map((option) => (
                    <MenuItem key={option.value} value={option.value}>
                        {option.label}
                    </MenuItem>
                ))
                }
            </TextField>
            <TextField id="outlined-basic" label="검색" variant="outlined" onChange={e => handleSearchKeyword(e)}/>
            <Button variant={"contained"} startIcon={<Search/>} onClick={e => handleSearch(e)}>Search</Button>

            <Button variant={"contained"} style={{float: "right"}} startIcon={<AddBox/>}
                    onClick={e => handleAddMovie(e)}>Add Movie</Button>

            <TableContainer component={Paper}>
                <Table sx={{minWidth: 700}} aria-label="customized table">
                    <TableHead>
                        <TableRow>
                            <StyledTableCell align="right">Post</StyledTableCell>
                            <StyledTableCell align="right">Title</StyledTableCell>
                            <StyledTableCell align="right">Review Count</StyledTableCell>
                            <StyledTableCell align="right">AVG Rating</StyledTableCell>
                            <StyledTableCell align="right">RegDate</StyledTableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {movieLists && movieLists.contents.map((movieList) => (
                            <StyledTableRow key={movieList.name} component={Link} to={`/read`} state={{mno: `${movieList.movieDTO.mno}`}}>
                                <StyledTableCell
                                    style={{width: '200px'}}>{!movieList.movieImageDTO.thumbnailURL.includes("test") &&
                                    <img style={{width: '200px', height: '140px'}}
                                         src={API_BASE_URL + '/display?fileName=' + movieList.movieImageDTO.thumbnailURL}/>
                                }
                                </StyledTableCell>
                                <StyledTableCell align="right">{movieList.movieDTO.title}</StyledTableCell>
                                <StyledTableCell align="right">{movieList.reviewCount}</StyledTableCell>
                                <StyledTableCell align="right">{movieList.starRating}</StyledTableCell>
                                <StyledTableCell align="right">{movieList.movieDTO.regDate}</StyledTableCell>
                            </StyledTableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>

            <MyPagination movieLists={movieLists} handleMovieLists={handleMovieLists} searchKeyword={searchKeyword}
                          searchType={selectItem}/>
        </Container>

    )
}

export default Home;
