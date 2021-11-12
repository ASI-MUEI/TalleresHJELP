import React from 'react';
import {Table} from 'react-bootstrap';
import {useTable} from 'react-table'
import {Link} from 'react-router-dom';

const Horario = ({reparaciones}) => {

    // Si la asistencia llega nula se devuelve Sin Asignar.
    // Si la asistencia tiene contenido, se devuelve un Link con la matricula
    const conversorAsistencia = index => {
        var asistencia = reparaciones[index]
        var resultado;

        if (asistencia === null) {
            resultado = <h6>Sin asignar</h6>
        } else {
            resultado = (
                <div>
                    <Link to={`/reparaciones/${asistencia.idAsistencia}`}>{asistencia.matricula}</Link>

                    {
                        // Si la asistencia está peritada, se muestra un indicativo
                        asistencia.peritaje ?
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                 className="bi bi-flag" viewBox="0 0 16 16">
                                <path
                                    d="M14.778.085A.5.5 0 0 1 15 .5V8a.5.5 0 0 1-.314.464L14.5 8l.186.464-.003.001-.006.003-.023.009a12.435 12.435 0 0 1-.397.15c-.264.095-.631.223-1.047.35-.816.252-1.879.523-2.71.523-.847 0-1.548-.28-2.158-.525l-.028-.01C7.68 8.71 7.14 8.5 6.5 8.5c-.7 0-1.638.23-2.437.477A19.626 19.626 0 0 0 3 9.342V15.5a.5.5 0 0 1-1 0V.5a.5.5 0 0 1 1 0v.282c.226-.079.496-.17.79-.26C4.606.272 5.67 0 6.5 0c.84 0 1.524.277 2.121.519l.043.018C9.286.788 9.828 1 10.5 1c.7 0 1.638-.23 2.437-.477a19.587 19.587 0 0 0 1.349-.476l.019-.007.004-.002h.001M14 1.221c-.22.078-.48.167-.766.255-.81.252-1.872.523-2.734.523-.886 0-1.592-.286-2.203-.534l-.008-.003C7.662 1.21 7.139 1 6.5 1c-.669 0-1.606.229-2.415.478A21.294 21.294 0 0 0 3 1.845v6.433c.22-.078.48-.167.766-.255C4.576 7.77 5.638 7.5 6.5 7.5c.847 0 1.548.28 2.158.525l.028.01C9.32 8.29 9.86 8.5 10.5 8.5c.668 0 1.606-.229 2.415-.478A21.317 21.317 0 0 0 14 7.655V1.222z"/>
                            </svg>
                            :
                            null
                    }
                </div>


            )
        }
        return resultado;
    }

    // eslint-disable-next-line
    const data = React.useMemo(
        () => [
            {
                col1: 'Elevador 1',
                col2: conversorAsistencia(1),
                col3: conversorAsistencia(2),
                col4: conversorAsistencia(3),
                col5: conversorAsistencia(4),
                col6: conversorAsistencia(5),
                col7: conversorAsistencia(6),
                col8: conversorAsistencia(7),
                col9: conversorAsistencia(8),
                col10: conversorAsistencia(9),
                col11: conversorAsistencia(10),
                col12: conversorAsistencia(11),
                col13: conversorAsistencia(12),
                col14: conversorAsistencia(13),
                col15: conversorAsistencia(14),
                col16: conversorAsistencia(15),
                col17: conversorAsistencia(16),
                col18: conversorAsistencia(17),
                col19: conversorAsistencia(18),
                col20: conversorAsistencia(19),
                col21: conversorAsistencia(20),
            },
            {
                col1: 'Elevador 2',
                col2: conversorAsistencia(21),
                col3: conversorAsistencia(22),
                col4: conversorAsistencia(23),
                col5: conversorAsistencia(24),
                col6: conversorAsistencia(25),
                col7: conversorAsistencia(26),
                col8: conversorAsistencia(27),
                col9: conversorAsistencia(28),
                col10: conversorAsistencia(29),
                col11: conversorAsistencia(30),
                col12: conversorAsistencia(31),
                col13: conversorAsistencia(32),
                col14: conversorAsistencia(33),
                col15: conversorAsistencia(34),
                col16: conversorAsistencia(35),
                col17: conversorAsistencia(36),
                col18: conversorAsistencia(37),
                col19: conversorAsistencia(38),
                col20: conversorAsistencia(39),
                col21: conversorAsistencia(40),
            },
            {
                col1: 'Elevador 3',
                col2: conversorAsistencia(41),
                col3: conversorAsistencia(42),
                col4: conversorAsistencia(43),
                col5: conversorAsistencia(44),
                col6: conversorAsistencia(45),
                col7: conversorAsistencia(46),
                col8: conversorAsistencia(47),
                col9: conversorAsistencia(48),
                col10: conversorAsistencia(49),
                col11: conversorAsistencia(50),
                col12: conversorAsistencia(51),
                col13: conversorAsistencia(52),
                col14: conversorAsistencia(53),
                col15: conversorAsistencia(54),
                col16: conversorAsistencia(55),
                col17: conversorAsistencia(56),
                col18: conversorAsistencia(57),
                col19: conversorAsistencia(58),
                col20: conversorAsistencia(59),
                col21: conversorAsistencia(60),
            },
            {
                col1: 'Elevador 4',
                col2: conversorAsistencia(61),
                col3: conversorAsistencia(62),
                col4: conversorAsistencia(63),
                col5: conversorAsistencia(64),
                col6: conversorAsistencia(65),
                col7: conversorAsistencia(66),
                col8: conversorAsistencia(67),
                col9: conversorAsistencia(68),
                col10: conversorAsistencia(69),
                col11: conversorAsistencia(70),
                col12: conversorAsistencia(71),
                col13: conversorAsistencia(72),
                col14: conversorAsistencia(73),
                col15: conversorAsistencia(74),
                col16: conversorAsistencia(75),
                col17: conversorAsistencia(76),
                col18: conversorAsistencia(77),
                col19: conversorAsistencia(78),
                col20: conversorAsistencia(79),
                col21: conversorAsistencia(80),
            },
            {
                col1: 'Elevador 5',
                col2: conversorAsistencia(81),
                col3: conversorAsistencia(82),
                col4: conversorAsistencia(83),
                col5: conversorAsistencia(84),
                col6: conversorAsistencia(85),
                col7: conversorAsistencia(86),
                col8: conversorAsistencia(87),
                col9: conversorAsistencia(88),
                col10: conversorAsistencia(89),
                col11: conversorAsistencia(90),
                col12: conversorAsistencia(91),
                col13: conversorAsistencia(92),
                col14: conversorAsistencia(93),
                col15: conversorAsistencia(94),
                col16: conversorAsistencia(95),
                col17: conversorAsistencia(96),
                col18: conversorAsistencia(97),
                col19: conversorAsistencia(98),
                col20: conversorAsistencia(99),
                col21: conversorAsistencia(100),
            },
        ],
    )

    const columns = React.useMemo(
        () => [
            {
                Header: 'Elevadores',
                accessor: 'col1', // accessor is the "key" in the data
            },
            {
                Header: '08:30',
                accessor: 'col2',
            },
            {
                Header: '09:00',
                accessor: 'col3',
            },
            {
                Header: '09:30',
                accessor: 'col4',
            },
            {
                Header: '10:00',
                accessor: 'col5',
            },
            {
                Header: '10:30',
                accessor: 'col6',
            },
            {
                Header: '11:00',
                accessor: 'col7',
            },
            {
                Header: '11:30',
                accessor: 'col8',
            },
            {
                Header: '12:00',
                accessor: 'col9',
            },
            {
                Header: '12:30',
                accessor: 'col10',
            },
            {
                Header: '13:00',
                accessor: 'col11',
            },
            {
                Header: '15:30',
                accessor: 'col12',
            },
            {
                Header: '16:00',
                accessor: 'col13',
            },
            {
                Header: '16:30',
                accessor: 'col14',
            },
            {
                Header: '17:00',
                accessor: 'col15',
            },
            {
                Header: '17:30',
                accessor: 'col16',
            },
            {
                Header: '18:00',
                accessor: 'col17',
            },
            {
                Header: '18:30',
                accessor: 'col18',
            },
            {
                Header: '19:00',
                accessor: 'col19',
            },
            {
                Header: '19:30',
                accessor: 'col20',
            },
            {
                Header: '20:00',
                accessor: 'col21',
            },
        ],
        []
    )

    const tableInstance = useTable({columns, data})

    const {
        getTableProps,
        getTableBodyProps,
        headerGroups,
        rows,
        prepareRow,
    } = tableInstance

    return (
        // apply the table props
        <Table responsive striped bordered hover {...getTableProps()} className="table table-striped table-hover">
            <thead>
            {// Loop over the header rows
                headerGroups.map(headerGroup => (
                    // Apply the header row props
                    <tr {...headerGroup.getHeaderGroupProps()}>
                        {// Loop over the headers in each row
                            headerGroup.headers.map(column => (
                                // Apply the header cell props
                                <th {...column.getHeaderProps()}>
                                    {// Render the header
                                        column.render('Header')}
                                </th>
                            ))}
                    </tr>
                ))}
            </thead>
            {/* Apply the table body props */}
            <tbody {...getTableBodyProps()}>
            {// Loop over the table rows
                rows.map(row => {
                    // Prepare the row for display
                    prepareRow(row)
                    return (
                        // Apply the row props
                        <tr {...row.getRowProps()}>
                            {// Loop over the rows cells
                                row.cells.map(cell => {
                                    // Apply the cell props
                                    return (
                                        <td {...cell.getCellProps()}>
                                            {// Render the cell contents
                                                cell.render('Cell')}
                                        </td>
                                    )
                                })}
                        </tr>
                    )
                })}
            </tbody>
        </Table>
    )
}

export default Horario;