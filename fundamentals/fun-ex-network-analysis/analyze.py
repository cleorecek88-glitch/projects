# Your code goes here!
# Remember, usage: python analyze.py path/to/data.csv -o path/to/analysis/

import argparse
import csv
from pathlib import Path
from simplegraphs import bar_chart

if __name__ == '__main__':
    parser = argparse.ArgumentParser()
    parser.add_argument('input', help='Input CSV file')
    parser.add_argument('-o', '--output', help='Output SVG file')
    args = parser.parse_args()

    # Step 1: ensure analysis directory exists
    analysis_dir = Path(args.output)
    analysis_dir.mkdir(exist_ok=True)

    # Step 2: read data from CSV file
    with open(args.input) as file:
        reader = csv.DictReader(file)
        data = list(reader)
    
    # Analysis 1: Connectedness analysis
    # Make a histogram of the number of connections per node

    connections = {}
    for row in data:
        connections[row['source']] = connections.get(row['source'], 0) + 1
        connections[row['target']] = connections.get(row['target'], 0) + 1

    min_connections = min(connections.values())
    max_connections = max(connections.values())
    frequency = {
        i: sum(1 for c in connections.values() if c == i)
        for i in range(min_connections, max_connections + 1)
    }
    
    with open(analysis_dir / 'connections.svg', 'w') as file:
        file.write(bar_chart(list(frequency.items())))

    # Analysis 3: The Pibling Counter
    # Make a histogram of the number of piblings per node

    def get_piblings(node):
        parents = {
            row['source']
            for row in data
            if row['target'] == node
            and row['type'] == 'parent'
        }

        piblings = parents.copy()
        for i in range(100):
            piblings |= {
                row['target']
                for row in data
                if row['source'] in piblings
                and row['type'] == 'sibling'
            } | {
                row['source']
                for row in data
                if row['target'] in piblings
                and row['type'] == 'sibling'
            }

        return piblings - parents

    nodes = {row['source'] for row in data} | {row['target'] for row in data}
    piblings = {node: len(get_piblings(node)) for node in nodes}

    min_piblings = min(piblings.values())
    max_piblings = max(piblings.values())
    frequency = {
        i: sum(1 for p in piblings.values() if p == i)
        for i in range(min_piblings, max_piblings + 1)
    }

    with open(analysis_dir / 'piblings.svg', 'w') as file:
        file.write(bar_chart(list(frequency.items())))

    # Analysis 4: The Nibling Counter
    # Make a histogram of the number of niblings per node

    def get_niblings(node):
        siblings = {node}
        for i in range(100):
            siblings |= {
                row['target']
                for row in data
                if row['source'] in siblings
                and row['type'] == 'sibling'
            } | {
                row['source']
                for row in data
                if row['target'] in siblings
                and row['type'] == 'sibling'
            }
        siblings -= {node}

        children = {
            row['target']
            for row in data
            if row['source'] in siblings
            and row['type'] == 'parent'
        }

        return children
    
    niblings = {node: len(get_niblings(node)) for node in nodes}

    min_niblings = min(niblings.values())
    max_niblings = max(niblings.values())
    frequency = {
        i: sum(1 for n in niblings.values() if n == i)
        for i in range(min_niblings, max_niblings + 1)
    }

    with open(analysis_dir / 'niblings.svg', 'w') as file:
        file.write(bar_chart(list(frequency.items())))
        