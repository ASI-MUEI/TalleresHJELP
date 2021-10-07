import React from 'react';
import { Table } from 'react-bootstrap';
import { useTable } from 'react-table'
import { Link } from 'react-router-dom';

const Horario = () => {

    const data = React.useMemo(
        () => [
          {
            col1: 'Elevador 1',
            col2: <Link to="/reparacion">BMW Serie 1 [9265LLC]</Link>,
            col3: <Link to="/reparacion">BMW Serie 1 [9265LLC]</Link>,
            col4: <Link to="/reparacion">BMW Serie 1 [9265LLC]</Link>,
            col5: <Link to="/reparacion">BMW Serie 1 [9265LLC]</Link>,
            col6: 'Sin Asignar',
            col7: <Link to="/reparacion">BMW Serie 5 [6286KMN]</Link>,
            col8: <Link to="/reparacion">BMW Serie 5 [6286KMN]</Link>,
            col9: 'Sin Asignar',
            col10: 'Sin Asignar',
            col11: 'Sin Asignar',
          },
          {
            col1: 'Elevador 2',
            col2: 'Sin Asignar',
            col3: 'Sin Asignar',
            col4: <Link to="/reparacion">Audi A3 [8247LFH]</Link>,
            col5: <Link to="/reparacion">Audi A3 [8247LFH]</Link>,
            col6: <Link to="/reparacion">Audi A3 [8247LFH]</Link>,
            col7: 'Sin Asignar',
            col8: 'Sin Asignar',
            col9: 'Sin Asignar',
            col10: 'Sin Asignar',
            col11: 'Sin Asignar',
          },
          {
            col1: 'Elevador 3',
            col2: <Link to="/reparacion">Citroën Berlingo [8247GFH]</Link>,
            col3: <Link to="/reparacion">Citroën Berlingo [8247GFH]</Link>,
            col4: <Link to="/reparacion">Citroën Berlingo [8247GFH]</Link>,
            col5: 'Sin Asignar',
            col6: 'Sin Asignar',
            col7: 'Sin Asignar',
            col8: 'Sin Asignar',
            col9: 'Sin Asignar',
            col10: 'Sin Asignar',
            col11: 'Sin Asignar',
          },
          {
            col1: 'Elevador 4',
            col2: 'Sin Asignar',
            col3: 'Sin Asignar',
            col4: 'Sin Asignar',
            col5: 'Sin Asignar',
            col6: 'Sin Asignar',
            col7: 'Sin Asignar',
            col8: 'Sin Asignar',
            col9: 'Sin Asignar',
            col10: 'Sin Asignar',
            col11: 'Sin Asignar',
          },
          {
            col1: 'Elevador 5',
            col2: 'Sin Asignar',
            col3: 'Sin Asignar',
            col4: 'Sin Asignar',
            col5: 'Sin Asignar',
            col6: 'Sin Asignar',
            col7: 'Sin Asignar',
            col8: <Link to="/reparacion">Renault Clio RS [8247GFH]</Link>,
            col9: <Link to="/reparacion">Renault Clio RS [8247GFH]</Link>,
            col10: 'Sin Asignar',
            col11: 'Sin Asignar',
          },
        ],
        []
      )

      const columns = React.useMemo(
        () => [
          {
            Header: 'Elevadores',
            accessor: 'col1', // accessor is the "key" in the data
          },
          {
            Header: '08:00',
            accessor: 'col2',
          },
          {
            Header: '08:30',
            accessor: 'col3',
          },
          {
            Header: '09:00',
            accessor: 'col4',
          },
          {
            Header: '09:30',
            accessor: 'col5',
          },
          {
            Header: '10:00',
            accessor: 'col6',
          },
          {
            Header: '10:30',
            accessor: 'col7',
          },
          {
            Header: '11:00',
            accessor: 'col8',
          },
          {
            Header: '11:30',
            accessor: 'col9',
          },
          {
            Header: '12:00',
            accessor: 'col10',
          },
          {
            Header: '12:30',
            accessor: 'col11',
          },

        ],
        []
      )

      const tableInstance = useTable({ columns, data })

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